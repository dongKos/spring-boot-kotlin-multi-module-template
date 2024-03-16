dependencies {
    implementation(project(":api:query"))
    implementation(project(":api:command"))

    // Logback
    implementation("ch.qos.logback.contrib:logback-json-classic:0.1.5")
    implementation("ch.qos.logback.contrib:logback-jackson:0.1.5")

    // infrastructure
    implementation(project(":infrastructure:jpa"))

    // common
    implementation(project(":common:util"))
}

tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}
