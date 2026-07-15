package com.space.ui.component.card

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.space.movieapp.core.ui.R
import com.space.ui.component.shimmer.shimmerEffect
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

/**
 * A composable component that represents a movie card in the catalogue list.
 * Displays the movie's poster, genre badge, title, release year, and a favorite toggle button.
 *
 * This component is optimized for grid grids and automatically handles long titles
 * by truncating them with an ellipsis.
 *
 * @param modifier The [Modifier] to be applied to the outer [Column] layout.
 * @param imgUrl The remote URL of the movie poster image to be loaded via Coil.
 * @param genre The text to be displayed inside the [GenreBadge] (e.g., "Action").
 * @param title The title of the movie. Truncated to a single line if too long.
 * @param isFavorite Indicates whether the movie is marked as a favorite, changing the icon state.
 * @param year The release year of the movie (e.g., "2024").
 * @param onFavoriteClick Callback lambda to be invoked when the favorite icon button is clicked.
 */

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
        // Card and genre badge together
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(Radius.radius16)
        ) {
            SubcomposeAsyncImage(
                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect()
                    )
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )

            // Genre
            if (genre.isNotEmpty()) {
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
        }

        Spacer(modifier = Modifier.height(Spacing.spacing04))

        // Title and favorite button
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
                    contentDescription = null
                )
            }
        }

        // Year
        Text(
            text = year,
            style = typography.labelMedium,
            color = colors.textSecondary
        )
    }
}