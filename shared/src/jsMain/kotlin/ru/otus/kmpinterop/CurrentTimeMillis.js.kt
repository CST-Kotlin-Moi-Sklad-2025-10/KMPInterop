package ru.otus.kmpinterop

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
actual fun currentTimeMillis(): Long {
    val millis = js("Date.now()") as Double
    return millis.toLong()
}