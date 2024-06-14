plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleDevtoolsKsp)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("kotlin-android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.dicoding.destinatik"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.destinatik"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Paging3
    implementation(libs.androidx.paging.runtime.ktx)

    //blur


    //swipe
    implementation(libs.androidx.swiperefreshlayout)

    //koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    //DataStore
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    //livedata
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Glide & Image Loading
    implementation(libs.glide)
    implementation (libs.shimmer)

    //splash screen
    implementation (libs.androidx.core.splashscreen)

    //image layout
    implementation (libs.circleimageview)

    //MAPS
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //room
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    testImplementation( libs.androidx.paging.common)

}