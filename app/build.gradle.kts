import java.io.FileInputStream
import java.util.Properties

apply("../ktlint.gradle")


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
            "THE_MOVIE_DATABASE_API",
            keystoreProperties.getProperty("THE_MOVIE_DATABASE_API")
        )
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib.jdk7)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)

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

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Picasso
    implementation(libs.picasso)
    implementation(libs.picasso.transformations)

    implementation(libs.recyclerview)

    // Groupie
    implementation(libs.groupie)
    implementation(libs.groupie.viewbinding)

    implementation(libs.timber)

    implementation(libs.retrofit)

    implementation(libs.converter.gson)

    implementation(libs.logging.interceptor)
}
