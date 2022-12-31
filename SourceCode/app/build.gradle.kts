android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "development.dreamcatcher.newsdemoapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    // Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.20")

    // Lifecycle components
    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")

    // AndroidX
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.core:core-ktx:1.8.0-alpha03")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    // Dagger 2
    implementation("com.google.dagger:dagger:2.28.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    implementation("androidx.compose.material3:material3:1.0.0-alpha02")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
    kapt("com.google.dagger:dagger-compiler:2.27")
    compileOnly("org.glassfish:javax.annotation:3.1.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")

    // Google Material IO
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.1.0")
    implementation("androidx.compose.ui:ui-tooling:1.1.0")
    implementation("androidx.compose.foundation:foundation:1.1.0")
    implementation("androidx.compose.material:material:1.1.0")
    implementation("androidx.compose.material:material-icons-core:1.1.0")
    implementation("androidx.compose.material:material-icons-extended:1.1.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-alpha02")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.1.0")

    // Jetpack Compose - MDC Theme
    implementation("com.google.android.material:compose-theme-adapter:1.1.3")

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.3.0")
    testImplementation("org.mockito:mockito-inline:2.13.0")
    testImplementation("android.arch.core:core-testing:1.1.1")

    // UI Testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

    // UI Testing - Jetpack Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.8.1")
    implementation("com.squareup.retrofit2:retrofit-mock:2.2.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

    // GSON
    implementation("com.google.code.gson:gson:2.8.6")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("com.github.bumptech.glide:annotations:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    // Coil (Jetpack Compose pictures loading)
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
}