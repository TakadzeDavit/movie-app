package com.space.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun MovieAppHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Sizing.size48)
            .padding(horizontal = Spacing.spacing16)
            .background(colors.background),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(Sizing.size24),
                painter = painterResource(id = R.drawable.icon_back_arrow),
                contentDescription = null,
                tint = colors.primaryText,
            )
        }

        Text(
            text = title,
            style = typography.titleMedium,
            color = colors.primaryText,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}