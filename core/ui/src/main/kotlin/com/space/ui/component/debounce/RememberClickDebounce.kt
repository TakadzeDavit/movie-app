package com.space.ui.component.debounce

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberDebouncedClick(
    debounceTime: Long = 900L,
    onClick: () -> Unit
): () -> Unit {
    var lastClickTime = remember { 0L }

    return remember(onClick) {
        {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= debounceTime) {
                lastClickTime = currentTime
                onClick()
            }
        }
    }
}