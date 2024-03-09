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
//        dataBinding = true
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
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


//    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    implementation ("androidx.paging:paging-runtime:3.0.0-alpha03")
//    androidTestImplementation 'org.testng:testng:7.3.0'
    kapt("androidx.room:room-compiler:2.6.1")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
//    kapt ("androidx.room:room-compiler:2.6.1")

//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"


//    implementation 'androidx.appcompat:appcompat:1.6.1'

//    implementation 'androidx.appcompat:appcompat:1.2.0'
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'


//    implementation 'com.squareup.wire:wire-runtime:4.4.3'
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-wire:2.9.0")

//    implementation 'org.greenrobot:eventbus:3.3.1'

    implementation ("io.insert-koin:koin-core:3.2.0")
    implementation ("io.insert-koin:koin-android:3.2.0")
    implementation ("io.insert-koin:koin-androidx-workmanager:3.2.0")

    androidTestImplementation ("io.insert-koin:koin-test:3.2.0")
//    androidTestImplementation "com.linkedin.dexmaker:dexmaker-mockito-inline-extended:2.28.3"
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test:rules:1.4.0'
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.0.0"
//    implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"

//    implementation 'com.google.guava:guava:27.0.1-android'
    //libre speed
//    implementation files('libs/fdossena.jar')
//    implementation 'org.java-websocket:Java-WebSocket:1.4.0'
//    implementation 'com.squareup.okio:okio:1.17.4'

    // Required -- JUnit 4 framework
//    testImplementation "junit:junit:4.13.2"
    // Optional -- Robolectric environment
//    testImplementation "androidx.test:core:1.5.0"
    // Optional -- Mockito framework
//    testImplementation "org.mockito:mockito-core:5.3.1"
    // Optional -- mockito-kotlin
//    testImplementation "org.mockito.kotlin:mockito-kotlin:5.1.0"
}