plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.thuuz.mob"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.thuuz.mob"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "7.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    debug {
      isDebuggable = true
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  testOptions {
    unitTests.isReturnDefaultValues = true
  }
}

tasks.withType<JavaCompile>().configureEach {
  exclude("com/actionbarsherlock/**")
  exclude("android/support/**")
  exclude("com/google/**")
  exclude("com/samsung/**")
  exclude("com/viewpagerindicator/**")
  exclude("org/joda/**")
  exclude("org/apache/**")
  exclude("twitter4j/**")
  exclude("com/thuuz/gtv/activity/**")
  exclude("com/thuuz/gtv/settings/**")
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3)
  implementation("androidx.compose.material:material-icons-extended")
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.coil.compose)
  implementation(libs.okhttp)
  implementation(libs.retrofit)
  implementation(libs.converter.moshi)
  implementation(libs.moshi.kotlin)
  implementation(libs.kotlinx.coroutines.android)
  implementation("androidx.work:work-runtime-ktx:2.9.1")
  testImplementation(libs.junit)
  testImplementation("org.json:json:20240303")
}
