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
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
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

    // modules
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.presentation)
    implementation(projects.feature.home.presentation)
    implementation(projects.feature.home.data)
    implementation(projects.feature.home.domain)
    implementation(projects.feature.home.api)
    implementation(projects.feature.favorites.presentation)
    implementation(projects.feature.favorites.api)
    implementation(projects.feature.details.presentation)
    implementation(projects.feature.details.data)
    implementation(projects.feature.details.domain)
    implementation(projects.feature.details.api)
}