package com.space.movieapp.feature.favorites.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.space.movieapp.feature.favorites.presentation.R
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing

@Composable
fun FavoritesHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Spacing.spacing10),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.favorite_movies),
            color = colors.primaryText,
            style = typography.titleMedium
        )
    }
}