plugins {
    alias(libs.plugins.movie.android.feature.data)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.movie.feature.home.data"
}

dependencies {
    implementation(libs.androidx.paging.common)
}