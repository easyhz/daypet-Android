import com.easyhz.daypet.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
            }
        }
    }
}