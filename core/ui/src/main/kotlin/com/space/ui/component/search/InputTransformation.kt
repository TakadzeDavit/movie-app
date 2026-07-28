package com.space.ui.component.search

import androidx.compose.foundation.text.input.InputTransformation

val preventLeadingSpaceTransformation = InputTransformation {
    if (asCharSequence().startsWith(" ")) {
        val trimmedText = asCharSequence().dropWhile { it.isWhitespace() }
        replace(0, length, trimmedText)
    }
}