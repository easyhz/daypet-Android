plugins {
    alias(libs.plugins.daypet.android.library)
    alias(libs.plugins.daypet.android.hilt)
    alias(libs.plugins.daypet.android.application.test)
}

android {
    namespace = "com.easyhz.daypet.database"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room DB
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}