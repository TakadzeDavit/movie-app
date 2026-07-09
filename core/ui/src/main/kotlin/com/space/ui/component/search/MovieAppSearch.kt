package com.space.ui.component.search

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
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
import com.space.movieapp.core.model.Genre
import com.space.movieapp.core.ui.R
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Sizing
import com.space.ui.theme.Spacing

/**
 * A highly interactive search bar component tailored for the movie application.
 *
 * This component features a [BasicTextField] styled with a custom decoration box,
 * dynamic search focus behavior (hiding the filter button and showing a "Cancel" button
 * when active), and an expandable horizontal list ([LazyRow]) of filter chips.
 *
 * ### Key Features:
 * * Automatically clears keyboard focus when the user presses the 'Search' IME action.
 * * Toggles the filter icon asset state based on whether the filters are expanded.
 * * Uses [AnimatedVisibility] to smoothly fade filter options in and out.
 *
 * @param searchQuery The current text query typed in the search field.
 * @param onSearchQueryChange Callback lambda invoked when the input text changes.
 * @param onFilterClick Callback lambda invoked when the filter toggle button is clicked.
 * @param areFiltersExpanded Controls the visibility state of the expandable filter chip section.
 * @param filterOptions A list of strings representing the titles of available filter choices (genres).
 * @param selectedOptionId The index of the currently active filter option, or null if none is selected.
 * @param onOptionSelected Callback lambda invoked when a specific filter chip is clicked, passing its index.
 * @param modifier The [Modifier] to be applied to the outermost container layout ([Column]).
 */

@Composable
fun MovieAppSearch(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    areFiltersExpanded: Boolean,
    filterOptions: List<Genre>,
    selectedOptionId: Int?,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
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
            .padding(vertical = Spacing.spacing08)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.spacing16),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .height(Sizing.size48)
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
                            .padding(horizontal = Spacing.spacing16),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = null,
                            tint = colors.textHint,
                            modifier = Modifier.size(Sizing.size22)
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

                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                painter = painterResource(R.drawable.icon_delete),
                                contentDescription = null,
                                tint = colors.textHint,
                                modifier = Modifier
                                    .size(Sizing.size18)
                                    .clickable {
                                        onSearchQueryChange(searchQuery.dropLast(1))
                                    }
                            )
                        }
                    }

                })

            if (isSearchFieldFocused || searchQuery.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = colors.primaryText,
                    style = typography.bodyMedium,
                    modifier = Modifier
                        .clickable {
                            onSearchQueryChange("")
                            focusManager.clearFocus()
                        }
                        .padding(
                            horizontal = Spacing.spacing04, vertical = Spacing.spacing08
                        ))
            } else {
                Box(
                    modifier = Modifier
                        .size(Sizing.size48)
                        .clip(CircleShape)
                        .background(colors.background), contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = onFilterClick, modifier = Modifier.size(Sizing.size36)
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
            visible = areFiltersExpanded && !isSearchFieldFocused && searchQuery.isEmpty(),
        ) {
            Column {
                Spacer(modifier = Modifier.height(Spacing.spacing12))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08),
                    contentPadding = PaddingValues(horizontal = Spacing.spacing16)
                ) {
                    items(
                        items = filterOptions,
                        key = { it.id }
                    ) { genre ->
                        val isSelected = genre.id == selectedOptionId

                        GenreChip(
                            title = genre.name,
                            isSelected = isSelected,
                            onChipClick = { onOptionSelected(genre.id) }
                        )
                    }
                }
            }
        }
    }
}