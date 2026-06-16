plugins {
    id("movieapp.android.application")
    id("movieapp.android.compose")
    alias(libs.plugins.kotlin.serialization)
}
android {
    namespace = "com.example.movieapp"

    defaultConfig {
        applicationId = "com.example.movieapp"
    }
}

dependencies {
    // splash
    implementation(libs.androidx.core.splashscreen)

    // tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // compose navigation
    implementation(libs.androidx.navigation.compose)

    // modules
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:catalogue:presentation"))
}