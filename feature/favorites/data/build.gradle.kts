plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.koin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.favorites.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.domain)
}