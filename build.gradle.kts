plugins {
    kotlin("jvm") version "2.0.0"
}

group = "me.kds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnit()
        }
    }
}
