plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.compose)
    alias(libs.plugins.movie.android.koin)
    alias(libs.plugins.movie.android.network)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.common"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}