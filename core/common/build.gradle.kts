plugins {
    id("movieapp.android.library")
    id("movieapp.android.compose")
    id("movieapp.android.koin")
    id("movieapp.android.network")
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