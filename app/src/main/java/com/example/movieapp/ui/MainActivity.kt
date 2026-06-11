package com.example.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.ui.theme.MovieAppTheme
import com.example.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Text(
                    modifier = Modifier.systemBarsPadding(),
                    text = "zdarovaaa",
                    style = MovieTheme.typography.bodyMedium
                )
            }
        }
    }
}