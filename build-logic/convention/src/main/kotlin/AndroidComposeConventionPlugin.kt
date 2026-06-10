import com.android.build.api.dsl.CommonExtension
import com.space.movieapp.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<CommonExtension>("android") {
                configureAndroidCompose(this)
            }
        }
    }
}