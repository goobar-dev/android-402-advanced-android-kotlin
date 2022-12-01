plugins {
  id("org.jetbrains.kotlin.jvm")
  id("java-library")
  id("com.google.devtools.ksp")
}

dependencies {
  implementation("androidx.room:room-common:2.4.3")

  implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
  ksp("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
}