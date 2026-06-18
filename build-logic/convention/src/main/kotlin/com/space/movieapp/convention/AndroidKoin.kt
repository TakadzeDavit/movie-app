package com.space.movieapp.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidKoin() {

    dependencies {
        add("implementation", libs.findLibrary("koin-core").get())
        add("implementation", libs.findLibrary("koin-android").get())
        add("implementation", libs.findLibrary("koin-androidx-compose").get())
    }
}