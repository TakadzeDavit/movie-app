package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.space.movieapp.core.navigation.NavigationCommand
import com.space.movieapp.core.navigation.requireGlobalNavigator
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.compose.currentKoinScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.viewmodel.defaultExtras
import org.koin.viewmodel.resolveViewModel
import kotlin.reflect.KClass

typealias VmClass<UIState, UIEvent> = KClass<out BaseVM<UIState, UIEvent>>

@OptIn(KoinInternalApi::class)
@Composable
internal fun <UIState : UiState, UIEvent : UiEvent> koinViewModel(
    vmClass: VmClass<UIState, UIEvent>,
    parameters: ParametersDefinition? = null,
): BaseVM<UIState, UIEvent> {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    return resolveViewModel(
        vmClass = vmClass,
        viewModelStore = viewModelStoreOwner.viewModelStore,
        extras = defaultExtras(viewModelStoreOwner),
        scope = currentKoinScope(),
        parameters = parameters
    )
}

@Composable
internal fun NavCommands(navigationCommands: MutableSharedFlow<NavigationCommand>) {
    val globalNavigator = requireGlobalNavigator()

    LaunchedEffect(Unit) {
        navigationCommands.collect {
            it.execute(
                globalNavigator
            )
        }
    }
}