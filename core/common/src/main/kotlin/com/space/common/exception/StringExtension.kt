package com.space.common.exception

fun String.toYear(): String {
    return this.substringBefore("-")
}