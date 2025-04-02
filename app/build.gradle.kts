plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.project.animeinsights"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.project.animeinsights"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    //Navigation
    implementation(libs.navigation.compose)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    //Hilt
    implementation(libs.hilt)
    implementation(libs.material3.android)
    implementation(libs.junit.ktx)
    implementation(libs.ui.tooling.preview.android)
    testImplementation(libs.hilt.android.testing)
    debugImplementation(libs.ui.tooling)
    ksp(libs.hilt.compiler)


    //Serialization
    implementation(libs.serialization)
    //Tests
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.truth)
    debugImplementation(libs.ui.test.manifest)
    testImplementation (libs.junit)
    testImplementation (libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test.v173)

    // retrofit
    implementation (libs.retrofit)
    // GSON
    implementation (libs.converter.gson)
    // coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)

    //Coil
    implementation(libs.coil.compose)

    //exo-player
    implementation (libs.exoplayer)


}