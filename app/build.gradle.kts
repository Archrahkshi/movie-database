import java.io.FileInputStream
import java.util.Properties

val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(rootProject.file("keystore.properties")))

android {
    namespace = "com.archrahkshi.moviedatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.archrahkshi.moviedatabase"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "API_KEY",
            keystoreProperties.getProperty("THE_MOVIE_DATABASE_API")
        )
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")

        // https://developer.themoviedb.org/reference/configuration-details
        buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/\"")
        buildConfigField("int", "POSTER_WIDTH", "500")
        buildConfigField("int", "BACKDROP_WIDTH", "1280")
        buildConfigField("int", "WATCHLIST_ITEM_WIDTH", "342")
        buildConfigField("int", "PROFILE_WIDTH", "185")
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
        release {
            enableUnitTestCoverage = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

kotlin.jvmToolchain(17)

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.kotlin.stdlib.jdk7)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Architecture components
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.picasso)
    implementation(libs.picasso.transformations)

    implementation(libs.groupie)
    implementation(libs.groupie.viewbinding)

    implementation(libs.timber)

    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)

    implementation(libs.logging.interceptor)

    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.rxjava3)

    implementation(libs.kotlinx.coroutines.android)
}
