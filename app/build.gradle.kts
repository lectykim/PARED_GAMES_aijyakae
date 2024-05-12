import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}
val properties = File(rootDir,"local.properties").inputStream().use {
    Properties().apply { load(it) }
}
val stableDiffusionApiKey = properties.getValue("STABLE_DIFFUSION_API_KEY") as String
val adUnitIdMovie = properties.getValue("AD_UNIT_ID_MOVIE") as String
val adUnitIdBanner = properties.getValue("AD_UNIT_ID_BANNER") as String
val deepLAuthKey = properties.getValue("DEEPL_AUTH_KEY") as String
android {
    namespace = "com.paredgames.aijyakae"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.paredgames.aijyakae"
        minSdk = 25
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        buildConfigField("String","STABLE_DIFFUSION_API_KEY", "\"$stableDiffusionApiKey\"")
        buildConfigField("String","AD_UNIT_ID_MOVIE", "\"$adUnitIdMovie\"")
        buildConfigField("String","AD_UNIT_ID_BANNER","\"$adUnitIdBanner\"")
        buildConfigField("String","DEEPL_AUTH_KEY","\"$deepLAuthKey\"")

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
        buildConfig=true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.datastore:datastore-core-android:1.1.0-rc01")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.github.skydoves:landscapist-glide:2.3.3")
    runtimeOnly("androidx.datastore:datastore-preferences:1.1.0")
    implementation("com.google.android.gms:play-services-ads:23.0.0")


}