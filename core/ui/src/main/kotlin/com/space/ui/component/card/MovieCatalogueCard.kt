package com.space.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun MovieCatalogueCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    genre: String,
    title: String,
    isFavorite: Boolean,
    year: String,
    onFavoriteClick: () -> Unit
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Column(
        modifier = modifier.width(Sizing.size162)
    ) {
        // card and genre together
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(Radius.radius16)
        ) {
            AsyncImage(
                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Genre
            GenreBadge(
                text = genre,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = Spacing.spacing10,
                        end = Spacing.spacing10
                    )
            )
        }

        Spacer(modifier = Modifier.height(Spacing.spacing04))

        // title and favorite button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = typography.bodyMedium,
                color = colors.primaryText,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            IconButton(
                modifier = Modifier.size(26.dp),
                onClick = onFavoriteClick
            ) {
                Icon(
                    painter = if (isFavorite) painterResource(R.drawable.icon_favorite_filled) else painterResource(
                        R.drawable.icon_favorite
                    ),
                    tint = colors.primary,
                    contentDescription = "Favorite"
                )
            }
        }

        // year
        Text(
            text = year,
            style = typography.labelMedium,
            color = colors.textSecondary
        )
    }
}