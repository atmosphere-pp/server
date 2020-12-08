import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val spekVersion = "2.0.14"
val kotlinVersion = "1.4.20"

repositories {
    jcenter()
}

plugins {
    kotlin("jvm") version "1.4.20"
}

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
}

tasks.test {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

// setup dependencies
dependencies {
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    testImplementation("io.strikt:strikt-core:0.28.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
