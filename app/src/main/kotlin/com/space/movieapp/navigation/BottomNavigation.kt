package com.space.movieapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import com.space.feature.favorites.api.FavoritesFeatureKey
import com.space.feature.home.api.HomeFeatureKey
import com.space.movieapp.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Radius
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

@Composable
fun MovieBottomBar(
    currentRoute: NavKey?,
    onNavigate: (NavKey) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.background)
            .padding(horizontal = Spacing.spacing16, vertical = Spacing.spacing12)
            .padding(bottom = Spacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16)
    ) {
        val items = listOf(Screen.HomeScreen, Screen.FavoritesScreen)

        items.forEach { screen ->
            val isSelected = currentRoute == screen.route

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(Sizing.size38)
                    .clip(Radius.radius08)
                    .background(if (isSelected) colors.primary else colors.surface)
                    .clickable { onNavigate(screen.route) },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(screen.icon),
                    contentDescription = stringResource(screen.title),
                    tint = if (isSelected) colors.background else colors.primaryText,
                    modifier = Modifier.size(Sizing.size18)
                )

                Spacer(modifier = Modifier.width(Spacing.spacing04))

                Text(
                    text = stringResource(screen.title),
                    style = typography.bodyMedium,
                    color = if (isSelected) colors.background else colors.primaryText
                )
            }
        }
    }
}

sealed class Screen(val route: NavKey, @param:StringRes val title: Int, @param:DrawableRes val icon: Int) {
    data object HomeScreen : Screen(route = HomeFeatureKey, title = R.string.home, R.drawable.icon_home)
    data object FavoritesScreen : Screen(route = FavoritesFeatureKey, title = R.string.favorites, R.drawable.icon_favorite)
}