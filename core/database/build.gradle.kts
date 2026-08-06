plugins {
    alias(libs.plugins.movie.android.library)
    alias(libs.plugins.movie.android.koin)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.space.core.database"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    ksp(libs.room.compiler)
}