plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.movieapp.feature.favorites.presentation"
}

dependencies {
    implementation(projects.feature.favorites.domain)
    implementation(projects.core.model)
}