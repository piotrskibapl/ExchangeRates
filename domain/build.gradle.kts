plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(libs.rxjava)
    implementation(libs.inject)
    testImplementation(libs.junit)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
