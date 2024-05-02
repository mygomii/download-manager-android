plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.HILT)
    kotlin("kapt")
}

android {
    namespace = "com.mygomii.presentation"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependencies.Google.MATERIAL)

    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.NAVIGATION_FRAGMENT_KTX)

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)
    implementation(platform(Dependencies.AndroidX.COMPOSE_BOM))

    implementation(Dependencies.AndroidX.UI)
    implementation(Dependencies.AndroidX.UI_GRAPHICS)
    implementation(Dependencies.AndroidX.UI_TOOLING_PREVIEW)
    implementation(Dependencies.AndroidX.MATERIAL3)

    androidTestImplementation(Dependencies.AndroidTest.ANDROID_X_JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ANDROID_X_ESPRESSO_CORE)

    debugImplementation(Dependencies.AndroidX.UI_TOOLING)
    debugImplementation(Dependencies.AndroidX.UI_TEST_MANIFEST)
    implementation(Dependencies.AndroidX.HILT_NAVIGATION_COMPOSE)

    testImplementation(Dependencies.JUNIT)
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)

    implementation(Dependencies.Hilt.ANDROID)
    kapt(Dependencies.Hilt.ANDROID_COMPILER)
}