plugins {
    alias(libs.plugins.movieapp.jvm.library)
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
}