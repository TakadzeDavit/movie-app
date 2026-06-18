import org.gradle.kotlin.dsl.dependencies

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin.api)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "movieapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidApplication") {
            id = "movieapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidCompose") {
            id = "movieapp.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("androidKoin") {
            id = "movieapp.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }

        register("androidNetwork") {
            id = "movieapp.android.network"
            implementationClass = "AndroidNetworkConventionPlugin"
        }

        register("androidFeature") {
            id = "movieapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}