plugins {
    id("movieapp.android.application")
    id("movieapp.android.compose")
}
android {
    namespace = "com.example.movieapp"

    defaultConfig {
        applicationId = "com.example.movieapp"
    }
}

dependencies {
    // tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // core-ui
    implementation(projects.core.ui)
}