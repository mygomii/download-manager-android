plugins {
    id(Plugins.APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    kotlin("kapt")
    id(Plugins.HILT)
    id(Plugins.KOTLIN_SERIALIZATION)
}

android {
    namespace = "com.mygomii.download_manager_android"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = "com.mygomii.download_manager_android"
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":presentation"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.MATERIAL3)
    implementation(Dependencies.AndroidX.NAVIGATION_UI_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)
    implementation(platform(Dependencies.AndroidX.COMPOSE_BOM))
    androidTestImplementation(Dependencies.AndroidTest.ANDROID_X_JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ANDROID_X_ESPRESSO_CORE)

    implementation(Dependencies.Hilt.ANDROID)
    kapt(Dependencies.Hilt.ANDROID_COMPILER)

    implementation(Dependencies.TED)
    implementation(Dependencies.LOGGER)
}