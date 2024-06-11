import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.easyhz.daypet.buildlogic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "daypet.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "daypet.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationTest") {
            id = "daypet.android.application.test"
            implementationClass = "AndroidApplicationTestConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "daypet.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "daypet.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "daypet.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "daypet.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidFirebase") {
            id = "daypet.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidFlavors") {
            id = "daypet.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("jvmLibrary") {
            id = "daypet.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}