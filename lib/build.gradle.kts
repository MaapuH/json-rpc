@file:OptIn(ExperimentalWasmDsl::class)

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.serialization)

    id("maven-publish")
}

group = "io.github.maapuh"
version = "1.0.0"

kotlin {
    compilerOptions {
        freeCompilerArgs.apply {
            add("-Xcontext-parameters")
            add("-Xsuppress-warning=NOTHING_TO_INLINE")
            add("-Xsuppress-warning=UNCHECKED_CAST")
        }
    }
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_20)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    js {
        browser()
        nodejs()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "io.github.maapuh.jsonrpc"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "jsonrpc", version.toString())

    pom {
        name = "JsonRPC"
        description = "JsonRPC for Kotlin, implemented using kotlinx.serialization"
        inceptionYear = "2025"
        url = "https://github.com/MaapuH/json-rpc"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "maapuh"
                name = "Alen MapuH"
                url = "https://github.com/MaapuH"
            }
        }
        scm {
            url = "https://github.com/MaapuH/json-rpc"
            connection = "scm:git:git://github.com/MaapuH/json-rpc.git"
            developerConnection = "scm:git:ssh://git@github.com/MaapuH/json-rpc.git"
        }
    }
}
