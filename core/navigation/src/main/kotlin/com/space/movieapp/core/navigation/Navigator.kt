package com.space.movieapp.core.navigation

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import java.util.concurrent.atomic.AtomicLong
import kotlin.collections.set
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalAtomicApi::class)
class Navigator(val backStack: NavBackStack<NavKey>) {
    private val popResultCallBacks = mutableMapOf<NavKey, (PopResult) -> Unit>()

    fun push(key: NavKey) {
        backStack.add(key)
    }

    fun <Result : PopResult> push(key: NavKey, onPopResult: ((Result) -> Unit)? = null) {
        if (onPopResult != null) {
            popResultCallBacks[key] = onPopResult as (PopResult) -> Unit
        }
        backStack.add(key)
    }

    fun pop(popResult: PopResult? = null) {
        val removed = backStack.removeLastOrNull() ?: return

        val callback = popResultCallBacks.remove(removed)
        if (callback != null && popResult != null) {
            callback.invoke(popResult)
        }
    }

    fun replaceLast(navKey: NavKey) {
        backStack[backStack.lastIndex] = navKey
    }

    fun bringToFront(key: NavKey) {
        backStack.remove(key)
        backStack.add(key)
    }
}

@Composable
fun rememberNavigator(initialKey: NavKey): Navigator {
    val backstack = rememberNavBackStack(initialKey)
    return remember { Navigator(backstack) }
}