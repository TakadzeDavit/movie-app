plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.favorites.api"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}