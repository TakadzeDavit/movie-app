plugins {
    alias(libs.plugins.movie.android.library)
}

android {
    namespace = "com.space.movie.core.presentation"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}