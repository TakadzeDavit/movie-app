package com.space.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun ButtonRefresh(
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {
    val typography = MovieTheme.typography
    val colors = MovieTheme.colors

    Box(
        modifier = Modifier
            .width(Sizing.size134)
            .height(Sizing.size44)
            .clip(Radius.radius16)
            .background(colors.primary)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colors.onPrimary
            )

            Spacer(modifier = Modifier.width(Spacing.spacing04))

            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = colors.onPrimary
            )
        }
    }
}