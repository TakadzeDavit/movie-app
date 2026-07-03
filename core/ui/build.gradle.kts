plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.compose)
}

android {
    namespace = "com.space.movieapp.core.ui"
}

dependencies {
    implementation(libs.bundles.coil)

    implementation(projects.core.model)
}