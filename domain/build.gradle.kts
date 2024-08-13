plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
