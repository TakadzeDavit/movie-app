plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.movieapp.feature.favorites.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.presentation)
    implementation(projects.feature.favorites.api)
    implementation(projects.feature.home.api)
    implementation(projects.feature.details.api)
}