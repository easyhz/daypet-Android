plugins {
    alias(libs.plugins.daypet.android.library.compose)
    alias(libs.plugins.daypet.android.feature)
    alias(libs.plugins.daypet.android.hilt)
}

android {
    namespace = "com.easyhz.daypet.home"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}