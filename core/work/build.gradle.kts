plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.application.test)
}

android {
    namespace = "com.easyhz.daypet.work"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.work)
}