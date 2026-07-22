package com.space.movieapp.feature.favorites.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.space.movieapp.feature.favorites.presentation.R
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing

@Composable
fun EmptyFavoriteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_no_results),
            tint = Color.Unspecified,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(Spacing.spacing26))

        Text(
            text = stringResource(R.string.no_movies_added_yet),
            color = colors.textSecondary,
            style = typography.titleMedium
        )
    }
}