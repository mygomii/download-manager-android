plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    kotlin("kapt")
    id(Plugins.HILT)
    id(Plugins.KOTLIN_SERIALIZATION)
}


android {
    namespace = "com.mygomii.data"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    testImplementation(Dependencies.JUNIT)


    androidTestImplementation(Dependencies.AndroidTest.ANDROID_X_JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.UI_TEST_JUNIT4)

    implementation(Dependencies.Ktor.CLIENT_CORE)
    implementation(Dependencies.Ktor.CLIENT_ANDROID)
    implementation(Dependencies.Ktor.CONTENT_NEGOTIATION)
    implementation(Dependencies.Ktor.JSON)
    implementation(Dependencies.Ktor.LOGGING)
    implementation(Dependencies.Ktor.OKHTTP)
    implementation(Dependencies.Ktor.CIO)

    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)

    implementation(Dependencies.Hilt.ANDROID)
    kapt(Dependencies.Hilt.ANDROID_COMPILER)
}