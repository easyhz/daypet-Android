import com.easyhz.daypet.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("daypet.android.library")
                apply("daypet.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:design-system"))
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}