plugins {
    kotlin("jvm") version "2.4.10"
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
