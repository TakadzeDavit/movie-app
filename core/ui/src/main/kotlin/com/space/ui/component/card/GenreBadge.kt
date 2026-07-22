package com.space.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing

@Composable
fun GenreBadge(
    text: String,
    modifier: Modifier = Modifier
) {
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

@Preview(showBackground = true)
@Composable
private fun GenreBadgePreview() {
    MovieAppTheme {
        Column(
            modifier = Modifier.background(colors.background),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacing16)
        ) {
            GenreBadge(text = "Action")

            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08)
            ) {
                GenreBadge(text = "Action")
                GenreBadge(text = "Drama")
                GenreBadge(text = "Horror")
            }
        }
    }
}