package com.space.ui.component.loader

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import com.space.ui.theme.Sizing

@Composable
fun MovieappCircularLoader(
    modifier: Modifier = Modifier,
    mainColor: Color,
    backgroundColor: Color,
    size: Dp = Sizing.size96
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RotationTransition")

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ),
        label = "RotationAngle"
    )

    val brush = remember(mainColor, backgroundColor) {
        Brush.sweepGradient(colors = listOf(backgroundColor, mainColor))
    }

    Canvas(modifier = modifier.size(size)) {
        rotate(rotationAngle) {
            drawCircle(
                brush = brush,
                radius = size.toPx() / 2
            )
        }
    }
}