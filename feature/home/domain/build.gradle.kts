plugins {
    alias(libs.plugins.movieapp.jvm.library)
}

dependencies {
    implementation(libs.androidx.paging.common)

    // modules
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.domain)
}