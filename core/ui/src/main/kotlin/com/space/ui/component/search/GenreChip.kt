package com.space.ui.component.search

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
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

/**
 * A custom chip component used to display movie genres or filter categories.
 *
 * This component supports two visual states:
 * - **Selected**: Filled with the theme's primary color and displays text in the `onPrimary` color.
 * - **Unselected**: Transparent background with a thin border using the `primaryText` color.
 *
 * It is built on top of [Surface] with a [CircleShape] to ensure consistent rounding and
 * handles click events seamlessly.
 *
 * @param title The text label to be displayed inside the chip (e.g., "Action", "Comedy").
 * @param isSelected Controls the visual state of the chip, indicating whether it is active/selected.
 * @param onChipClick Lambda expression invoked when the user clicks on the chip.
 */

@Composable
fun GenreChip(
    title: String,
    isSelected: Boolean,
    onChipClick: () -> Unit
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Surface(
        modifier = Modifier
            .height(Sizing.size22)
            .clickable { onChipClick() },
        shape = CircleShape,
        color = if (isSelected) colors.primary else Color.Transparent,
        border = if (isSelected) null else BorderStroke(0.5.dp, colors.primaryText)
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = Spacing.spacing12,
                vertical = Spacing.spacing04
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = if (isSelected) colors.onPrimary else colors.primaryText,
                style = typography.labelSmall
            )
        }
    }
}