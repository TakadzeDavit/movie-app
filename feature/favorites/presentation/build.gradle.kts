plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.movieapp.feature.favorites.presentation"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.presentation)
}