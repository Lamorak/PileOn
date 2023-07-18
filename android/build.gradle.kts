plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(project(":common"))
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "cz.lamorak.pileon.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}