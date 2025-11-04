package ru.otus.kmpinterop

expect class DecimalFormat(pattern: String) {
    fun format(value: Double): String
}