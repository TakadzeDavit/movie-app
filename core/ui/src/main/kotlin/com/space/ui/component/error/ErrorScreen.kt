package com.space.ui.component.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.space.movieapp.core.ui.R
import com.space.ui.component.button.ButtonRefresh
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing

/**
 * A full-screen error placeholder view designed to handle and display application failures.
 *
 * This component acts as a user-friendly error state screen, providing:
 * - A prominent error visual/icon to signal that something went wrong.
 * - A bold, descriptive title summarizing the nature of the error.
 * - A secondary description offering more context or troubleshooting steps.
 * - An actionable refresh button at the bottom to let users retry the failed operation.
 *
 * It takes full control of the screen space using [Modifier.fillMaxSize] and centers its
 * content vertically and horizontally for maximum visual balance.
 *
 * @param title The primary short headline of the error (e.g., "Connection Error").
 * @param description A more detailed explanation of the error or a helpful hint for the user.
 * @param onRefreshClick Lambda block executed when the user taps the refresh/retry button.
 */

@Composable
fun ErrorScreen(
    title: String,
    description: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.spacing64),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_error),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(Spacing.spacing28))

        Text(
            text = title,
            style = typography.headlineSmall,
            color = colors.primaryText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.spacing22))

        Text(
            text = description,
            style = typography.titleMedium,
            color = colors.textSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.spacing110))

        ButtonRefresh(
            text = stringResource(R.string.refresh),
            iconRes = R.drawable.icon_refresh,
            onClick = onRefreshClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    MovieAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            ErrorScreen(
                title = "Something went wrong",
                description = "Please check your internet connection and try again.",
                onRefreshClick = {}
            )
        }
    }
}