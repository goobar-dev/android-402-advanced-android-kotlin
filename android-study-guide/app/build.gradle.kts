plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "dev.goobar.androidstudyguide"
  compileSdk=33

  defaultConfig {
    applicationId = "dev.goobar.androidstudyguide"
    minSdk = 23
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  compileOptions {
    sourceCompatibility=JavaVersion.VERSION_1_8
    targetCompatibility=JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.3.1"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

kapt {
  correctErrorTypes = true
}

val compose_ui_version = "1.3.1"
dependencies {
  implementation(project(":data"))
  implementation(project(":analytics"))

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
  implementation("androidx.compose.ui:ui:$compose_ui_version")
  implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
  implementation("androidx.compose.material:material:1.3.1")
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-compiler:2.44")


  testImplementation("junit:junit:4.13.2")

  debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
  debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")
}