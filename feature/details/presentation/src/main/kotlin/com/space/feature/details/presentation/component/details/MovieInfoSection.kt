package com.space.feature.details.presentation.component.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.space.feature.details.presentation.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun MovieInfoSection(
    title: String,
    rating: Double,
    genre: String,
    duration: String,
    year: Int,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.spacing16, vertical = Spacing.spacing26),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = typography.titleLarge,
                color = colors.primaryText,
            )

            Icon(
                painter = painterResource(
                    if (isFavorite) R.drawable.icon_filled_favorite
                    else R.drawable.icon_favorite_outlined
                ),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier.clickable { onFavoriteClick() }
            )
        }

        Spacer(modifier = Modifier.height(Spacing.spacing26))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieInfoTag(
                text = rating.toString(),
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.icon_rate_star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(Sizing.size12)
                    )
                }
            )

            MovieInfoTag(text = genre)

            MovieInfoTag(
                text = duration,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_clock),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(Sizing.size16)
                    )
                }
            )

            MovieInfoTag(text = year.toString())
        }
    }
}
