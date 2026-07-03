package com.space.feature.details.presentation.vm

import com.space.feature.details.presentation.contract.DetailsEvent
import com.space.feature.details.presentation.contract.DetailsState
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.EmptySideEffect

class DetailsViewModel :
    BaseViewModel<DetailsState, DetailsEvent, EmptySideEffect>(DetailsState()) {
    override fun onEvent(event: DetailsEvent) {

    }
}