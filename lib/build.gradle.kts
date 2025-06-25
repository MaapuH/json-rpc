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

val githubNickname = "MaapuH"
val githubRepoName = "kmp.jsonrpc"

val artifactName = "kmp-jsonrpc"

kotlin {
    compilerOptions {
        freeCompilerArgs.apply {
            add("-Xcontext-parameters")
            add("-Xwarning-level=NOTHING_TO_INLINE:disabled")
            add("-Xwarning-level=UNCHECKED_CAST:disabled")
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

    watchosX64()
    watchosArm64()
    watchosSimulatorArm64()

    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    js {
        browser()
        nodejs()
    }

    wasmJs()
    wasmWasi()

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
    namespace = "$group.$githubRepoName"
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

    coordinates(group.toString(), artifactName, version.toString())

    pom {
        name = "KMPJsonRPC"
        description = "JsonRPC for Kotlin Multiplatform, implemented using kotlinx.serialization"
        inceptionYear = "2025"
        url = "https://github.com/$githubNickname/$githubRepoName"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = githubNickname
                name = "Alen MapuH"
                url = "https://github.com/$githubNickname"
            }
        }
        scm {
            url = "https://github.com/$githubNickname/$githubRepoName"
            connection = "scm:git:git://github.com/$githubNickname/$githubRepoName.git"
            developerConnection = "scm:git:ssh://git@github.com/$githubNickname/$githubRepoName.git"
        }
    }
}
