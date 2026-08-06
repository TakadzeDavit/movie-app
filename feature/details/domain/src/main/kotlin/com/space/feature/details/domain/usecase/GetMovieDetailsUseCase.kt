package com.space.feature.details.domain.usecase

import com.space.feature.details.domain.repository.DetailsRepository

class GetMovieDetailsUseCase(
    private val detailsRepository: DetailsRepository
) {
    operator fun invoke(movieId: Int) = detailsRepository.getMovieDetails(movieId = movieId)
}