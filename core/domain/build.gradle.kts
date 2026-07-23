plugins {
    alias(libs.plugins.movieapp.jvm.library)
}

dependencies {
    implementation(libs.androidx.paging.common)

    implementation(projects.core.common)
}