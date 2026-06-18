package com.space.ui.component.search_filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.space.movieapp.core.model.Genre
import com.space.ui.theme.MovieTheme

@Composable
fun GenreChip(
    genre: Genre,
    isSelected: Boolean,
    onChipClick: () -> Unit
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Surface(
        modifier = Modifier
            .height(22.dp)
            .clickable { onChipClick() },
        shape = CircleShape,
        color = if (isSelected) colors.primary else Color.Transparent,
        border = if (isSelected) null else BorderStroke(1.dp, colors.primaryText)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = genre.toDisplayString(),
                color = if (isSelected) colors.onPrimary else colors.primaryText,
                style = typography.bodyMedium
            )
        }
    }
}