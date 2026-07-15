plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.network)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.feature.details.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.feature.details.domain)
}