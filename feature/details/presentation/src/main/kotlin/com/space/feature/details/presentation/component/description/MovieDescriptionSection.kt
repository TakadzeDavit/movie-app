package com.space.feature.details.presentation.component.description

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.space.feature.details.presentation.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun MovieDescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    val typography = MovieTheme.typography
    val colors = MovieTheme.colors

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.spacing16),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing08)
    ) {
        Text(
            text = stringResource(R.string.about_movie),
            style = typography.titleMedium,
            color = colors.primaryText
        )

        Text(
            text = description,
            style = typography.bodyMedium,
            color = colors.textSecondary
        )
    }
}