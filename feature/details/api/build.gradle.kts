plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.compose)
}

android {
    namespace = "com.space.feature.details.api"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}