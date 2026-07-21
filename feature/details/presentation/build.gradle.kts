plugins {
    alias(libs.plugins.movie.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.details.presentation"
}

dependencies {
    implementation(projects.feature.details.domain)
    implementation(projects.feature.details.api)
    implementation(projects.feature.favorites.api)
    implementation(projects.feature.home.api)
    implementation(projects.core.presentation)
    implementation(projects.core.domain)
}