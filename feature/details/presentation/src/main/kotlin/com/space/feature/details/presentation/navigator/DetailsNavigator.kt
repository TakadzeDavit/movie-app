package com.space.feature.details.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.feature.details.api.DetailsFeatureKey
import com.space.feature.details.presentation.screen.DetailsScreen

fun EntryProviderScope<NavKey>.detailsEntry() {
    entry<DetailsFeatureKey> { key ->
        DetailsScreen(
            movieId = key.movieId
        )
    }
}