plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    /** Kotlin core **/
    /** implementation("androidx.core:core-ktx:1.8.0") **/
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
    
    /** Coroutines  **/
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2")

    /** Hilt **/
    implementation ("com.google.dagger:hilt-core:2.42")

}
