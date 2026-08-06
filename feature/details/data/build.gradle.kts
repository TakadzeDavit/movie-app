plugins {
    alias(libs.plugins.movie.android.feature.data)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.details.data"
}