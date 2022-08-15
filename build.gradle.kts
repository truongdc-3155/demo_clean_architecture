// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath ("com.google.gms:google-services:4.3.13")
    }
}
plugins {
    id ("com.android.application") version "7.2.1" apply false
    id ("com.android.library") version "7.2.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
    kotlin ("kapt") version "1.7.10"
}

tasks.register( "clean", Delete::class.java) {
    delete (rootProject.buildDir)
}
