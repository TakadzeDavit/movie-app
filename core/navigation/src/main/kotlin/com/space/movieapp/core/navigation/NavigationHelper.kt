package com.space.movieapp.core.navigation

import androidx.navigation3.runtime.NavKey

data object FeatureNavigationHelper : NavigationHelper

interface NavigationHelper {

    fun push(
        key: NavKey,
    ) = NavigationCommand.Push<Nothing>(key)

    fun <Result : PopResult> push(
        key: NavKey,
        onPopResult: ((Result) -> Unit)? = null
    ) = NavigationCommand.Push(key, onPopResult)

    fun replace(key: NavKey) = NavigationCommand.Replace(key)

    fun pop(result: PopResult? = null) = NavigationCommand.Pop(result)
}