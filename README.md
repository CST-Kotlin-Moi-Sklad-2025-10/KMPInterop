# KMPInterop

A Kotlin Multiplatform project demonstrating a stopwatch application that runs on Android, iOS, and Web platforms with native UI implementations.

## Project Structure

- `shared/` - Shared Kotlin Multiplatform module containing the business logic
  - `commonMain/` - Common Kotlin code (ViewModel, interfaces)
  - `androidMain/` - Android-specific implementations
  - `iosMain/` - iOS-specific implementations
  - `jsMain/` - JavaScript/Web-specific implementations
- `androidApp/` - Android application with Jetpack Compose UI
- `iosApp/` - iOS application with SwiftUI
- `webapp/` - React TypeScript web application

## Features

- Stopwatch functionality with start/stop controls
- Shared business logic across all platforms
- Native UI for each platform:
  - Android: Jetpack Compose
  - iOS: SwiftUI
  - Web: React with TypeScript

## Building and Running

### Prerequisites

- JDK 11 or higher
- Android Studio for Android development
- Xcode for iOS development (macOS only)
- Node.js and npm for web development

### Android

1. Open the project in Android Studio
2. Select the `androidApp` run configuration
3. Run on an emulator or physical device

Alternatively, from the command line:
```bash
./gradlew :androidApp:installDebug
```

### iOS

1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select a simulator or device
3. Build and run (Cmd+R)

### Web

The web application uses an automated npm-based integration with the Kotlin/JS shared module.

#### First Time Setup

1. Navigate to the webapp directory:
```bash
cd webapp
```

2. Install dependencies:
```bash
npm install
```

The `npm install` command automatically triggers a preinstall hook that:
- Builds the Kotlin/JS shared module
- Generates the npm package structure
- Installs it as a local dependency

#### Running the Web App

After installation, start the development server:
```bash
npm start
```

The app will open in your browser at `http://localhost:3000`

#### During Development

When you make changes to the shared Kotlin module, use the compile script to rebuild and reload:
```bash
sh scripts/compile.sh
```

This script will:
- Rebuild the Kotlin/JS module
- Reinstall it in the React app
- Start the development server

#### Building for Production

```bash
npm run build
```

The production build will be available in `webapp/build/`

## Architecture

The project uses a shared ViewModel pattern where:
- Business logic is written once in Kotlin (in `shared/commonMain`)
- Platform-specific implementations are provided for time APIs and formatting
- Each platform implements its own UI layer that consumes the shared ViewModel
- The web platform uses Kotlin/JS to compile the shared code to JavaScript

## Development Workflow

When making changes to the shared module:

1. Make your changes in `shared/src/commonMain/`
2. For Android/iOS: Simply rebuild the app in Android Studio or Xcode
3. For web development: Run the compile script from the `webapp` directory:
   ```bash
   cd webapp
   sh scripts/compile.sh
   ```
   This will rebuild the Kotlin/JS module and restart the dev server with your changes

## License

This project is for educational purposes.
