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
}

tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}
