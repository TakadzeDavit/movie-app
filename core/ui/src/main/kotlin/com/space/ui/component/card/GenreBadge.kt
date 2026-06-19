package com.space.ui.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun GenreBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = colors.primary
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = Spacing.spacing12,
                vertical = Spacing.spacing04
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = typography.labelSmall,
                color = colors.onPrimary
            )
        }
    }
}