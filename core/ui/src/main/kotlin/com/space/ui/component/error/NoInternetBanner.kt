package com.space.ui.component.error

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Spacing

/**
 * An adaptive banner component that notifies the user about their network connectivity status.
 *
 * This banner automatically handles its own visibility using smooth transitions:
 * - **Visible**: When [isOnline] is `false`, it slides up from the bottom and fades in.
 * - **Hidden**: When [isOnline] is `true`, it slides down and fades out seamlessly.
 *
 * It is typically placed at the bottom of the screen (e.g., on the Home screen) to provide
 * non-intrusive feedback when the network drops during active scrolling or browsing.
 *
 * @param isOnline Current network connectivity state. The banner is only displayed if this is `false`.
 * @param modifier The [Modifier] to be applied to the root [AnimatedVisibility] container for custom positioning.
 */

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