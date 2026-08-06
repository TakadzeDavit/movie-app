package com.space.common.extension

fun String.toYear(): String {
    return this.substringBefore("-")
}