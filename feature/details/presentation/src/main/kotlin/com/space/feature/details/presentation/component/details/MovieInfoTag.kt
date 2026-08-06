package com.space.feature.details.presentation.component.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

@Composable
fun MovieInfoTag(
    text: String,
    icon: (@Composable () -> Unit)? = null
) {
    if(text.isNotEmpty()) {
        Row(
            modifier = Modifier
                .background(color = colors.surface, shape = Radius.radius16)
                .padding(horizontal = Spacing.spacing10, vertical = Spacing.spacing04),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.spacing04)
        ) {
            if (icon != null) icon()

            Text(
                text = text,
                style = typography.bodyMedium,
                color = colors.textSecondary
            )
        }
    }
}