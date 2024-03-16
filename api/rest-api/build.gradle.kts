dependencies {
    // api
    implementation(project(":api:query"))
    implementation(project(":api:command"))
    implementation(project(":api:signing"))

    // infrastructure
    implementation(project(":infrastructure:jpa"))
    implementation(project(":infrastructure:security"))

    // common
    implementation(project(":common:util"))

    // Logback
    implementation("ch.qos.logback.contrib:logback-json-classic:0.1.5")
    implementation("ch.qos.logback.contrib:logback-jackson:0.1.5")
}

tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}
