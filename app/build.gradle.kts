plugins {
    alias(libs.plugins.movie.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.movie.android.compose)
    alias(libs.plugins.movie.android.koin)
}
android {
    namespace = "com.space.movieapp"

    defaultConfig {
        applicationId = "com.space.movieapp"
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

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
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:favorites:presentation"))
    implementation(project(":feature:details:presentation"))
    implementation(project(":feature:details:data"))
    implementation(project(":feature:details:domain"))
}