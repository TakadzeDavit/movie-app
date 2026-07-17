plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.koin)
}

android {
    namespace = "com.space.movie.core.presentation"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
}