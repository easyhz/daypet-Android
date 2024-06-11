plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.hilt)
    alias(libs.plugins.daypet.android.application.test)
}

android {
    namespace = "com.easyhz.daypet.data"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}