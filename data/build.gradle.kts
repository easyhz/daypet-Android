plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.hilt)
    alias(libs.plugins.daypet.android.application.test)
    alias(libs.plugins.daypet.android.application.firebase)
}

android {
    namespace = "com.easyhz.daypet.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}