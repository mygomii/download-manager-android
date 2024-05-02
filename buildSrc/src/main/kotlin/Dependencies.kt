object Dependencies {

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.ANDROID_X_APP_COMPAT_VERSION}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.ANDROID_X_CONSTRAINT_LAYOUT_VERSION}"
        const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.ANDROID_X_NAVIGATION_FRAGMENT_KTX_VERSION}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.ANDROID_X_NAVIGATION_UI_KTX_VERSION}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROID_X_CORE_KTX_VERSION}"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROID_X_LIFECYCLE_RUNTIME_KTX_VERSION}"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ANDROID_X_ACTIVITY_COMPOSE_VERSION}"
        const val COMPOSE_BOM = "androidx.compose:compose-bom:${Versions.ANDROID_X_COMPOSE_BOM_VERSION}"
        const val UI = "androidx.compose.ui:ui"
        const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"

        const val MATERIAL3 = "androidx.compose.material3:material3"
        const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.ANDROID_X_HILT_COMPOSE_VERSION}"
    }

    object AndroidTest {
        const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
        const val ANDROID_X_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_X_JUNIT_VERSION}"
        const val ANDROID_X_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ANDROID_X_ESPRESSO_CORE_VERSION}"
    }

    object Hilt {
        const val ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_ANDROID_COMPILER_VERSION}"
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.HILT_ANDROID_VERSION}"
    }

    object Coroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
    }

    object Ktor {
        const val CLIENT_CORE = "io.ktor:ktor-client-core:${Versions.KTOR_VERSION}"
        const val CLIENT_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR_VERSION}"
        const val CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR_VERSION}"
        const val JSON = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR_VERSION}"
        const val LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR_VERSION}"
        const val OKHTTP = "io.ktor:ktor-client-okhttp:${Versions.KTOR_VERSION}"
        const val CIO = "io.ktor:ktor-client-cio:${Versions.KTOR_VERSION}"
    }

    object Google {
        const val JSON = "com.google.code.gson:gson:${Versions.JSON_VERSION}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    }

    const val JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    const val DATASTORE = "androidx.datastore:datastore-preferences:1.0.0"


    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val LOGGER = "com.orhanobut:logger:${Versions.LOGGER_VERSION}"

    const val TED = "io.github.ParkSangGwon:tedpermission-normal:3.3.0"
}
