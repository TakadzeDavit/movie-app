package com.space.ui.component.search_filter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing

@Composable
fun MovieAppSearch(
    searchQuery: String,
    areFiltersExpanded: Boolean,
    selectedOptionIndex: Int?,
    filterOptions: List<String>,
    modifier: Modifier = Modifier,
    onSearchQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    val filterIconAsset = if (areFiltersExpanded) {
        R.drawable.icon_filter_filled
    } else {
        R.drawable.icon_filter_outlined
    }

    val focusManager = LocalFocusManager.current
    var isSearchFieldFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.background)
            .padding(vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .onFocusChanged { focusState ->
                        isSearchFieldFocused = focusState.isFocused
                    },
                textStyle = typography.bodyMedium.copy(color = colors.primaryText),
                cursorBrush = SolidColor(colors.primaryText),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .background(colors.surface)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = null,
                            tint = colors.textHint,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(Spacing.spacing08))

                        Box(modifier = Modifier.weight(1f)) {
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.search),
                                    color = colors.textHint,
                                    style = typography.bodyMedium
                                )
                            }
                            innerTextField()
                        }
                    }
                })

            if (isSearchFieldFocused) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = colors.textSecondary,
                    style = typography.bodyMedium,
                    modifier = Modifier
                        .clickable {
                            onSearchQueryChange("")
                            focusManager.clearFocus()
                        }
                        .padding(horizontal = 4.dp, vertical = 8.dp))
            } else {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(colors.background),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = onFilterClick, modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(filterIconAsset),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = areFiltersExpanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column {
                Spacer(modifier = Modifier.height(Spacing.spacing12))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08),
                    contentPadding = PaddingValues(horizontal = Spacing.spacing16)
                ) {
                    itemsIndexed(
                        items = filterOptions,
                        key = { index, optionTitle -> optionTitle }
                    ) { index, optionTitle ->
                        val isSelected = index == selectedOptionIndex

                        GenreChip(
                            title = optionTitle,
                            isSelected = isSelected,
                            onChipClick = { onOptionSelected(index) }
                        )
                    }
                }
            }
        }
    }
}