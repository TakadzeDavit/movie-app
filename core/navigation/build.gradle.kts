plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.movie.android.compose)
}


android {
    namespace = "com.space.movieapp.core.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}