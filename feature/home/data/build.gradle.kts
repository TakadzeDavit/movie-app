plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.network)

}

android {
    namespace = "com.space.movie.feature.home.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.feature.home.domain)
}