package com.space.feature.details.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class DetailsFeatureKey(val movieId: Int) : NavKey