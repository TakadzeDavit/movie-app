import com.space.movieapp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNetworkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            dependencies {
                add("implementation", libs.findLibrary("retrofit-core").get())
                add("implementation", libs.findLibrary("okhttp-logging-interceptor").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
                add("implementation", libs.findLibrary("retrofit-converter-serialization").get())
            }
        }
    }
}