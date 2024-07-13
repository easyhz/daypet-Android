plugins {
    alias(libs.plugins.daypet.android.application)
    alias(libs.plugins.daypet.android.application.compose)
    alias(libs.plugins.daypet.android.application.flavors)
    alias(libs.plugins.daypet.android.application.test)
    alias(libs.plugins.daypet.android.application.firebase)
    alias(libs.plugins.daypet.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhz.daypet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.easyhz.daypet"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designSystem)
    implementation(projects.core.work)

    implementation(projects.data)
    implementation(projects.domain)

    implementation(projects.feature.home)
    implementation(projects.feature.memoryDetail)
    implementation(projects.feature.uploadMemory)
    implementation(projects.feature.sign)
    implementation(projects.feature.splash)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.kotlinx.serialization.json)
    implementation("androidx.core:core-splashscreen:1.0.0")
}