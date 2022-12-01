plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
  id("com.google.devtools.ksp")
}

val STUDY_GUIDE_SERVICE_URL: String by project

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

    buildConfigField("String", "STUDY_GUIDE_SERVICE_URL", "\"$STUDY_GUIDE_SERVICE_URL\"")
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

  implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
  implementation("androidx.compose.ui:ui:$compose_ui_version")
  implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
  implementation("androidx.compose.material:material:1.3.1")
  implementation("androidx.navigation:navigation-compose:2.5.3")
  implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
  implementation("com.google.accompanist:accompanist-placeholder-material:0.28.0")

  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-compiler:2.44")

  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
  implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
  ksp("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

  // used for Room database
  implementation("androidx.room:room-runtime:2.4.2")
  ksp("androidx.room:room-compiler:2.4.2")
  implementation("androidx.room:room-ktx:2.4.2")


  testImplementation("junit:junit:4.13.2")

  debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
  debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")
}