package com.space.ui.component.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

/**
 * A placeholder view displayed when a search query yields no results.
 *
 * This component provides clear, centered visual feedback to the user, consisting of:
 * - A circular background containing a search magnifier icon.
 * - A primary bold text indicating that no results were found.
 * - A secondary helper text suggesting the user try their search again with different keywords.
 *
 * It utilizes standard project layout tokens ([Spacing], [Sizing]) and tints to maintain
 * absolute visual consistency with the application's overall design system.
 *
 * @param modifier The [Modifier] to be applied to the root [Column] for custom positioning or sizing within the screen.
 */

@Composable
fun EmptyResultView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.spacing64),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(Sizing.size96)
                .background(colors.surface.copy(alpha = 0.5f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null,
                tint = colors.textHint,
                modifier = Modifier.size(Sizing.size36)
            )
        }

        Spacer(modifier = Modifier.height(Spacing.spacing16))

        Text(
            text = stringResource(R.string.no_results_found),
            style = typography.titleMedium,
            color = colors.primaryText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.spacing08))

        Text(
            text = stringResource(R.string.try_again_text),
            style = typography.bodyMedium,
            color = colors.textHint,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = Spacing.spacing28)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyResultViewPreview() {
    MovieAppTheme {
        Column(
            modifier = Modifier
                .background(colors.background)
                .padding(Spacing.spacing16)
        ) {
            EmptyResultView()
        }
    }
}