import com.space.movieapp.convention.configureAndroidKoin
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroidKoin()
        }
    }
}