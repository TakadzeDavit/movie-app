plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.network)
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.space.movie.feature.home.data"
}

dependencies {
    implementation(libs.androidx.paging.common)

    // modules
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.common)
    implementation(projects.feature.home.domain)
    implementation(projects.core.model)
}