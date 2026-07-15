package com.space.ui.component.error

import android.graphics.Movie
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun NetworkStatusBanner(
    isOnline: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    AnimatedVisibility(
        visible = !isOnline,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surface, shape = Radius.radius16)
                .padding(Spacing.spacing08),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_internet_connection_you_are_offline),
                color = colors.primaryText,
                style = typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}