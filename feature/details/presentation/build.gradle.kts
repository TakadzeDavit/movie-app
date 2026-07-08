plugins {
    alias(libs.plugins.movie.android.feature)
}

android {
    namespace = "com.space.feature.details.presentation"

}

dependencies {
    implementation(projects.feature.details.domain)
    implementation(projects.core.model)
    implementation(projects.core.presentation)
    implementation(projects.core.domain)
}