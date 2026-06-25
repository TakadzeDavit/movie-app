plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.network)
    alias(libs.plugins.kotlin.serialization)
}


android {
    namespace = "com.space.movieapp.core.navigation"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}