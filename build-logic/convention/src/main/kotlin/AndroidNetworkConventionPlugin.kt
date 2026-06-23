import com.android.build.api.dsl.LibraryExtension
import com.space.movieapp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.util.Properties

class AndroidNetworkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("movieapp.android.koin")

            val properties = Properties()
            val propertiesFile = project.rootProject.file("local.properties")
            if (propertiesFile.exists()) {
                properties.load(propertiesFile.inputStream())
            }

            val apiToken = properties.getProperty("API_TOKEN") ?: "\"\""
            val baseUrl = properties.getProperty("BASE_URL") ?: "\"\""

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    buildConfig = true
                }
                compileSdk = 37

                defaultConfig {
                    minSdk = 26

                    buildConfigField("String", "API_TOKEN", apiToken)
                    buildConfigField("String", "BASE_URL", baseUrl)
                }
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", libs.findLibrary("retrofit-core").get())
                add("implementation", libs.findLibrary("okhttp-logging-interceptor").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
                add("implementation", libs.findLibrary("retrofit-converter-serialization").get())
            }
        }
    }
}