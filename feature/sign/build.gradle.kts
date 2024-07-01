import java.util.Properties

plugins {
    alias(libs.plugins.daypet.android.library.compose)
    alias(libs.plugins.daypet.android.feature)
    alias(libs.plugins.daypet.android.hilt)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.daypet.sign"

    buildTypes {
        debug {
            buildConfigField("String", "GOOGLE_CLIENT_ID", localProperties["google.client.id"].toString())
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core.common)
    implementation(projects.core.designSystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}