plugins {
    kotlin("multiplatform") version "1.9.20"
    id("com.android.application") version "8.1.0"
    id("org.jetbrains.compose") version "1.5.10"
}

kotlin {
    androidTarget()
    // База для iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosMain by creating {
            dependsOn(commonMain)
        }
    }
}

android {
    namespace = "org.example.geoblinker"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
