package com.space.feature.details.presentation.component.poster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.space.feature.details.presentation.R
import com.space.ui.component.button.ButtonTrailer
import com.space.ui.component.shimmer.shimmerEffect
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun MoviePosterSection(
    posterUrl: String,
    onTrailerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.76f)
    ) {
        SubcomposeAsyncImage(
            model = posterUrl,
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
                    painter = painterResource(com.space.movieapp.core.ui.R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        )

        ButtonTrailer(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = Spacing.spacing22, end = Spacing.spacing16),
            text = stringResource(R.string.trailer),
            iconRes = R.drawable.icon_trailer,
            onClick = onTrailerClick
        )
    }
}