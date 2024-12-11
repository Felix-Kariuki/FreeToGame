import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    kotlin("plugin.serialization").version("1.9.20")
    //id("com.squareup.sqldelight").version("1.5.5")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // Navigator
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)


            implementation(libs.runtime)
            implementation(libs.coroutines.extensions)
            implementation(libs.kotlinx.datetime)

            // implementation(libs.kamel.image)
            // implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            // implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")


        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
//            implementation(libs.kotlinx.coroutines.swing)
//            implementation(libs.ktor.client.okhttp)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            //implementation(libs.android.driver)
            implementation(libs.appcompat)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)

            implementation(libs.koin.android)
            implementation("io.coil-kt:coil-compose:2.5.0")
            // implementation(libs.datastore.preferences)
            //implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
        }

        iosMain.dependencies {

            implementation(libs.ktor.client.darwin)
            //implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

        }

    }
}



android {
    namespace = "com.flexcode.freetogame"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    kotlin {
//        jvmToolchain(11)
//    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.activity.ktx)
    commonMainApi(libs.mvvm.core)
    commonMainApi(libs.mvvm.compose)
    commonMainApi(libs.mvvm.flow)
    commonMainApi(libs.mvvm.flow.compose)

}

compose.desktop {
    application {
        mainClass = "com.flexcode.freetogame.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.flexcode.freetogame"
            packageVersion = "1.0.0"
        }
    }
}
