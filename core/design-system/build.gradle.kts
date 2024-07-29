import java.util.Properties

plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.library.compose)
    alias(libs.plugins.daypet.android.application.test)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.daypet.design_system"
    buildTypes {
        debug {
            buildConfigField("String", "EMPTY_URL", localProperties["empty.url"].toString())
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.glide)
    implementation(libs.lottie)
}