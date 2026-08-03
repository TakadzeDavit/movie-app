import com.space.movieapp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("movieapp.android.library")
            pluginManager.apply("movieapp.android.compose")
            pluginManager.apply("movieapp.android.koin")

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:navigation"))
                add("implementation", project(":core:presentation"))
                add("implementation", project(":core:domain"))
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.findBundle("coil").get())
                add("compileOnly", libs.findLibrary("androidx-navigation-compose").get())
            }
        }
    }
}