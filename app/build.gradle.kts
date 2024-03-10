import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt") version "1.9.23"
}

android {
    namespace = "com.kueski.marktest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kueski.marktest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY_NAME", properties.getProperty("API_KEY"))
        buildConfigField("String", "BASE_URL_V3", "\"https://api.themoviedb.org/3/\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    dataBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.paging:paging-runtime:3.0.0-alpha03")
    kapt("androidx.room:room-compiler:2.6.1")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")

//    testImplementation ("org.mockito:mockito-inline:2.13.0")
//    androidTestImplementation ("org.mockito:mockito-inline:2.13.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("io.insert-koin:koin-android:3.2.0")
    androidTestImplementation("io.insert-koin:koin-test:3.2.0")

//    androidTestImplementation 'androidx.test:runner:1.3.0'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.4'
//    androidTestImplementation 'androidx.test:rules:1.5.0'

    androidTestImplementation ("androidx.test:rules:1.5.0")
//    androidTestImplementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
//    androidTestImplementation "org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version"
    //For using viewModelScope
//For runBlockingTest, CoroutineDispatcher etc.
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")


    androidTestImplementation("androidx.test:runner:1.5.2")
//    testImplementation("androidx.arch.core:core-testing:2.1.0")
    // Required -- JUnit 4 framework
//    testImplementation "junit:junit:4.13.2"
    // Optional -- Robolectric environment
//    testImplementation "androidx.test:core:1.5.0"
    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:4.9.0")
    testImplementation ("app.cash.turbine:turbine:0.2.1")
//    androidTestImplementation ("org.mockito:mockito-core:4.9.0")
//    testImplementatandroidTestImplementationion 'org.mockito:mockito-core:3.10.0'
    androidTestImplementation ("org.mockito:mockito-android:4.9.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")

    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    // Optional -- mockito-kotlin
//    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.1.0")
}