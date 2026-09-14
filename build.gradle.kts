plugins {
    kotlin("jvm") version "2.4.20"
}

group = "me.kds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnit()
        }
    }
}
