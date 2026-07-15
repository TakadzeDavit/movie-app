package com.space.feature.details.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String
)