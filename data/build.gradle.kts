import java.util.Properties

plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.hilt)
    alias(libs.plugins.daypet.android.application.test)
    alias(libs.plugins.daypet.android.application.firebase)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.easyhz.daypet.data"
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
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}