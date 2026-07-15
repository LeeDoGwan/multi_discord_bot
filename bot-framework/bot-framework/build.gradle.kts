plugins {
    kotlin("jvm") version "2.3.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
    api("net.dv8tion:JDA:6.5.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}