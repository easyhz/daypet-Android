plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.library.compose)
    alias(libs.plugins.daypet.android.application.test)
}

android {
    namespace = "com.easyhz.daypet.common"
}

dependencies {

    implementation(libs.androidx.core.ktx)
}