package ru.otus.kmpinterop

actual class DecimalFormat actual constructor(private val pattern: String) {
    actual fun format(value: Double): String {
        // Parse pattern to determine decimal places
        val decimalPlaces = pattern.substringAfter('.', "").length
        return value.asDynamic().toFixed(decimalPlaces) as String
    }
}

