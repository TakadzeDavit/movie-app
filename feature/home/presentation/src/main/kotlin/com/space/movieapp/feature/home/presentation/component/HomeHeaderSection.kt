package com.space.movieapp.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import com.space.core.domain.model.Genre
import com.space.ui.component.search.GenreChip
import com.space.ui.component.search.MovieAppSearch
import com.space.ui.theme.Spacing

@Composable
fun HomeHeaderSection(
    searchState: TextFieldState,
    areFiltersExpanded: Boolean,
    filters: List<Genre>,
    selectedGenreId: Int?,
    onFilterClick: (Int) -> Unit,
    onFilterIconClick: () -> Unit
) {
    MovieAppSearch(
        searchState = searchState,
        onFilterClick = onFilterIconClick,
        areFiltersExpanded = areFiltersExpanded,
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08),
            contentPadding = PaddingValues(horizontal = Spacing.spacing16)
        ) {
            items(
                items = filters,
                key = { it.id }
            ) { genre ->
                val isSelected = genre.id == selectedGenreId

                GenreChip(
                    title = genre.name,
                    isSelected = isSelected,
                    onChipClick = { onFilterClick(genre.id) }
                )
            }
        }
    }
}