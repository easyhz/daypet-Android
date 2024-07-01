import com.easyhz.daypet.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
            }

            dependencies {
                val bom = libs.findLibrary("firebase-bom").get()
                add("implementation", platform(bom))
                
                "implementation"(libs.findLibrary("firebase.firestore.ktx").get())
                "implementation"(libs.findLibrary("firebase.auth.ktx").get())
                "implementation"(libs.findLibrary("gms.play.services.auth").get())
//                "implementation"(libs.findLibrary("firebase.crashlytics").get())
            }
        }
    }
}