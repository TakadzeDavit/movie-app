package com.space.ui.component.error

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.space.movieapp.core.ui.R
import com.space.ui.component.button.ButtonRefresh
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun ErrorScreen(
    title: String,
    description: String,
    onRefreshClick: () -> Unit
) {
    val typography = MovieTheme.typography
    val colors = MovieTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_error),
            contentDescription = "Error icon",
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

        Spacer(modifier = Modifier.height(110.dp))

        ButtonRefresh(text = "Refresh", iconRes = R.drawable.icon_refresh, onClick = onRefreshClick)
    }
}