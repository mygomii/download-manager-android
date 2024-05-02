// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id (Plugins.APPLICATION) version Versions.AGP_VERSION apply false
    id (Plugins.KOTLIN_ANDROID) version Versions.KOTLIN_VERSION apply false
    id (Plugins.HILT) version Versions.HILT_ANDROID_VERSION apply false
    id (Plugins.KOTLIN_SERIALIZATION) version Versions.KOTLIN_PLUGIN_SERIALIZATION_VERSION apply false
}