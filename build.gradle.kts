plugins {
    kotlin("jvm") version "1.6.21"
}
group = "net.sayaya"
repositories {
    mavenCentral()
}
dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

tasks.withType<Test> {
    useJUnitPlatform()
}