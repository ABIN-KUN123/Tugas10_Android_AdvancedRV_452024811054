// app/build.gradle.kts (Module :app)

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

kotlin {
    jvmToolchain(11)
}

android {
    namespace = "com.example.advancedrv"
    compileSdk = 37 // <-- DIUBAH (Dari 35 ke 37 untuk mengatasi eror AAR Metadata)

    defaultConfig {
        applicationId = "com.example.advancedrv"
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    // <-- DITAMBAHKAN: Mengatur Compiler Option agar Jetpack Compose
    // tetap aman mendeteksi basis Java yang digunakan.
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8" // Menyesuaikan kompiler internal Compose
    }
}

dependencies {
    // AndroidX Utama dari version catalog proyek Anda
    implementation(libs.androidx.core.ktx)

    // Compose (Diperlukan karena ada file Theme.kt yang menggunakan @Composable)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Pustaka XML UI & RecyclerView yang diwajibkan untuk Tugas 10
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")

    // Pengujian (Testing)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}