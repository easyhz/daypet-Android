plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.library.compose)
}

android {
    namespace = "com.easyhz.daypet.design_system"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}