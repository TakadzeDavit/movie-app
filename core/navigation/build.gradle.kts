plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.kotlin.serialization)
}


android {
    namespace = "com.space.movieapp.core.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}