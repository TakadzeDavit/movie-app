package com.space.movieapp.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import kotlin.jvm.optionals.getOrNull

internal fun Project.configureAndroidKoin() {
    pluginManager.apply("io.insert-koin.compiler.plugin")

    dependencies {
        libs.findLibrary("koin-core").ifPresent {
            add("implementation", it)
        }
        libs.findLibrary("koin-android").ifPresent {
            add("implementation", it)
        }
        libs.findLibrary("koin-androidx-compose").ifPresent {
            add("implementation", it)
        }
        libs.findLibrary("koin-annotations").ifPresent {
            add("implementation", it)
        }
    }
}