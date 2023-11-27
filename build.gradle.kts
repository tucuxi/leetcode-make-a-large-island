plugins {
    kotlin("jvm") version "1.9.21"
    id("io.gitlab.arturbosch.detekt").version("1.23.3")
}

group = "me.kds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}
