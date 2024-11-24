plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.wallet_hci"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.wallet_hci"
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
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json.v163)
    // implementation("androidx.compose.material3:material3-adaptive:1.2.0")
    // implementation("androidx.window:window:1.1.0")
    // implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.retrofit) // Core Retrofit library
    implementation(libs.converter.gson) // For JSON parsing, if using Gson
    implementation(libs.kotlinx.serialization.json.v151) // Kotlin Serialization
    implementation(libs.retrofit2.kotlinx.serialization.converter) // For Kotlin Serialization
    implementation(libs.hilt.android)
    implementation (libs.retrofit)
    implementation (libs.okhttp3.logging.interceptor)
    implementation (libs.kotlinx.serialization.json.v163)
    implementation (libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)
    implementation(libs.androidx.window)
    implementation(libs.androidx.core)
    
    // implementation(libs.androidx.compose.material3.adaptive)
    // implementation(libs.androidx.compose.material3.adaptive.layout)
    // implementation(libs.androidx.compose.material3.adaptive.navigation)
    // implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
}