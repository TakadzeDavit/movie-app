plugins {
    id("movieapp.android.library")
    id("movieapp.android.compose")
}

android {
    namespace = "com.example.ui"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}