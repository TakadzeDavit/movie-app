package com.space.ui.component.search_filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.space.movieapp.core.model.Genre
import com.space.movieapp.core.ui.R

@Composable
fun Genre.toDisplayString(): String {
    val resId = when (this) {
        Genre.COMEDY -> R.string.comedy
        Genre.DRAMA -> R.string.drama
        Genre.ROMANCE -> R.string.romance
        Genre.HORROR -> R.string.horror
        Genre.SCIENCE_FICTION -> R.string.science_fiction
    }
    return stringResource(resId)
}