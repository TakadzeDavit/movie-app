import com.space.movieapp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("movieapp.android.library")
            pluginManager.apply("movieapp.android.network")

            dependencies {
                add("implementation", project(":core:network"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":feature:${featureName()}:domain"))
            }
        }
    }

    private fun Project.featureName(): String {
        val segments = path.split(":").filter { it.isNotEmpty() }
        val featureIndex = segments.indexOf("feature")
        return segments.getOrNull(featureIndex + 1)
            ?: error("Feature name not found in path: $path")
    }
}