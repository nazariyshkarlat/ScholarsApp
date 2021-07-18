package com.example.composeexample.buildsrc

object Configs {
    const val CompileSdkVersion = 30
    const val MinSdkVersion = 23
    const val TargetSdkVersion = 30

    const val VersionCode = 1
    const val VersionName = "1.0"
}

private const val kotlinVersion = "1.5.10"

object ClassPaths {
    private const val gradleVersion = "7.1.0-alpha03"
    const val androidGradlePlugin = "com.android.tools.build:gradle:$gradleVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
}

object Compose {
    private const val composeVersion = "1.0.0-beta09"
    private const val composeActivityVersion = "1.3.0-beta02"
    private const val composeNavigationVersion = "2.4.0-alpha03"
    private const val composeLifecycleViewModelVersion = "1.0.0-alpha07"

    const val animation = "androidx.compose.animation:animation:$composeVersion"
    const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiUtil = "androidx.compose.ui:ui-util:$composeVersion"
    const val uiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val activityCompose = "androidx.activity:activity-compose:$composeActivityVersion"
    const val navigationCompose = "androidx.navigation:navigation-compose:$composeNavigationVersion"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$composeLifecycleViewModelVersion"
}

object Tests {
    private const val junitVersion = "4.13.2"
    private const val junitKtx = "1.1.2"

    const val junit = "junit:junit:$junitVersion"
    const val junitKotlin = "androidx.test.ext:junit-ktx:$junitKtx"
}

object Core {
    private const val androidXVersion = "1.3.2"
    private const val appCompatVersion = "1.3.0"
    private const val materialVersion = "1.3.0"

    const val androidXCore = "androidx.core:core-ktx:$androidXVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val material = "com.google.android.material:material:$materialVersion"
}

object Libs {

    object Coroutines {
        private const val version = "1.5.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Koin {
        private const val version = "3.1.1"

        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val androidCompat = "io.insert-koin:koin-android-compat:$version"
        const val test = "io.insert-koin:koin-test:$version"

    }

    object Room {
        private const val version = "2.4.0-alpha03"

        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Coil {
        private const val version = "1.2.2"

        const val library = "io.coil-kt:coil:$version"
        const val base = "io.coil-kt:coil-base:$version"
    }

    object Ktor {
        private const val version = "1.6.0"

        const val client = "io.ktor:ktor-client-android:$version"
        const val logging = "io.ktor:ktor-client-logging-jvm:$version"
        const val serializationClient = "io.ktor:ktor-client-serialization-jvm:$version"
    }

    object Serialization {
        private const val version = "0.82.0"
        private const val kotlinxVersion = "1.2.1"
        private const val xmlPullVersion = "1.1.3.1"
        private const val kXmlVersion = "2.3.0"

        const val kXml = "net.sf.kxml:kxml2:$kXmlVersion"
        const val xmlPull = "xmlpull:xmlpull:$xmlPullVersion"
        const val core = "io.github.pdvrieze.xmlutil:core-android:$version"
        const val serialization = "io.github.pdvrieze.xmlutil:serialization-android:$version"
        const val ktor = "io.github.pdvrieze.xmlutil:ktor:$version"
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxVersion"
    }

}
