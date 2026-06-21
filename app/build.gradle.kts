plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.movie.android.application)
    alias(libs.plugins.movie.android.compose)
}
android {
    namespace = "com.space.movieapp"

    defaultConfig {
        applicationId = "com.space.movieapp"
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
    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:favorites:presentation"))
}