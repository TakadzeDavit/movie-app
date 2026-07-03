package com.space.feature.details.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.space.feature.details.presentation.vm.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("kdopskdposa")
        Text("kdopskdposa")
        Text("kdopskdposa")
        Text("kdopskdposa")
        Text("kdopskdposa")
        Text("kdopskdposa")
    }

}