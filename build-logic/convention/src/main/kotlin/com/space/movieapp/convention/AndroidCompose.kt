package com.space.movieapp.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    baseExtension: CommonExtension
) {
    baseExtension.apply {
        buildFeatures.compose = true
    }

    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    dependencies {
        // Compose BOM
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        // UI
        add("implementation", libs.findLibrary("androidx-activity-compose").get())
        add("implementation", libs.findLibrary("androidx-compose-material3").get())
        add("implementation", libs.findLibrary("androidx-compose-ui").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        add("implementation", libs.findLibrary("androidx-core-ktx").get())

        // tools
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
        add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())

        // navigation3
        add("implementation", libs.findLibrary("androidx-navigation3-runtime").get())
        add("implementation", libs.findLibrary("androidx-navigation3-ui").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get())
    }
}