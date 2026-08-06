package com.space.movieapp.core.navigation

import androidx.navigation3.runtime.NavKey

sealed interface NavigationCommand {
    fun execute(navigator: Navigator)

    data class Replace(val key: NavKey) : NavigationCommand {
        override fun execute(navigator: Navigator) {
            navigator.replaceLast(key)
        }
    }

    data class Push<Result : PopResult>(
        val key: NavKey,
        val onPopResult: ((Result) -> Unit)? = null
    ) : NavigationCommand {
        override fun execute(navigator: Navigator) {
            navigator.push(key, onPopResult)
        }
    }

    data class Pop(val result: PopResult? = null) : NavigationCommand {
        override fun execute(navigator: Navigator) {
            navigator.pop(result)
        }
    }
}