plugins {
 id("com.android.application")
 id("org.jetbrains.kotlin.android")
 id("org.jetbrains.kotlin.plugin.compose")
}
android {
 namespace = "com.hamsat.app"
 compileSdk = 35
 defaultConfig { applicationId = "com.hamsat.app"; minSdk = 26; targetSdk = 35; versionCode = 10; versionName = "HAMSAT GOLD v10" }
 buildFeatures { compose = true }
 compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
 kotlinOptions { jvmTarget = "17" }
}
dependencies {
 implementation(platform("androidx.compose:compose-bom:2024.12.01"))
 implementation("androidx.activity:activity-compose:1.9.3")
 implementation("androidx.compose.ui:ui")
 implementation("androidx.compose.ui:ui-graphics")
 implementation("androidx.compose.material3:material3")
 implementation("androidx.compose.material:material-icons-extended")
}
