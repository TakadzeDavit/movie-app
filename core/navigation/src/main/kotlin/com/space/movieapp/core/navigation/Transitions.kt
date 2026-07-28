package com.space.movieapp.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.ui.unit.IntOffset
import androidx.navigation3.scene.Scene

private const val NAV_ANIMATION_DURATION = 350

private val navFloatAnimationSpec: FiniteAnimationSpec<Float> = tween(
    durationMillis = NAV_ANIMATION_DURATION,
    easing = FastOutSlowInEasing
)

private typealias NavTransitionSpec<T> = AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform
private typealias NavPredictivePopTransitionSpec<T> = AnimatedContentTransitionScope<Scene<T>>.(Int) -> ContentTransform

fun <T : Any> featureTransitionSpec(): NavTransitionSpec<T> = {
    fadeIn(animationSpec = navFloatAnimationSpec) togetherWith fadeOut(animationSpec = navFloatAnimationSpec)
}

fun <T : Any> featurePopTransitionSpec(): NavTransitionSpec<T> = {
    fadeIn(animationSpec = navFloatAnimationSpec) togetherWith fadeOut(animationSpec = navFloatAnimationSpec)
}

fun <T : Any> featurePredictivePopTransitionSpec(): NavPredictivePopTransitionSpec<T> = { _ ->
    featurePopTransitionSpec<T>().invoke(this)
}