# KMP Interop - Web Application

This project is a React web application that integrates with a Kotlin Multiplatform (KMP) shared module.

## Kotlin/JS Integration

This webapp uses an automated npm-based integration with the `KMPInterop-shared` Kotlin/JS module. The integration is managed through:

- **Preinstall Hook**: Automatically builds the Kotlin/JS module before package installation
- **Local Package**: The shared module is installed as a local npm dependency from `file:../shared/build/dist`

### How It Works

1. When you run `npm install`, the preinstall hook (`scripts/preinstall.sh`) automatically:
   - Cleans and builds the Kotlin/JS shared module using Gradle
   - Generates a package.json for the module
   - Copies compiled output to `shared/build/dist/`
   - Renames module files to `index.*` for cleaner imports

2. The React app imports the shared module as a standard npm package:
   ```typescript
   import { createStopwatchViewModel } from '`KMPInterop-shared`';
   ```

## Available Scripts

### `npm install`

Installs all dependencies and triggers the preinstall hook to build the Kotlin/JS module.\
**Run this first** when setting up the project or after pulling changes to the shared module.

### `sh scripts/compile.sh`

Development helper script that:
- Removes the old `KMPInterop-shared` package
- Reinstalls it from the latest build
- Starts the development server

Use this during development when you make changes to the Kotlin/JS shared module.

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

## Development Workflow

1. **Initial Setup**:
   ```bash
   cd webapp
   npm install
   ```

2. **Start Development Server**:
   ```bash
   npm start
   ```

3. **After Changing Shared Kotlin Code**:
   ```bash
   sh scripts/compile.sh
   ```

## Project Structure

- `src/` - React application source code
- `scripts/` - Build and integration scripts
  - `preinstall.sh` - Automated Kotlin/JS build script
  - `compile.sh` - Development rebuild helper
- `public/` - Static assets

## Learn More

- [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started)
- [React documentation](https://reactjs.org/)
- [Kotlin/JS documentation](https://kotlinlang.org/docs/js-overview.html)
