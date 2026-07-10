plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.movieapp.feature.home.presentation"
}

dependencies {
    implementation(projects.feature.home.domain)
}