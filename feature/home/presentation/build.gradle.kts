plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.movieapp.feature.home.presentation"
}

dependencies {
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)

    // modules
    implementation(projects.feature.home.domain)
    implementation(projects.feature.home.api)
    implementation(projects.feature.details.api)
    implementation(projects.feature.favorites.api)
    implementation(projects.core.domain)
}