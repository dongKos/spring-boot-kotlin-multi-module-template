dependencies {
    // domain
    implementation(project(":domain:user"))

    // infrastructure
    implementation(project(":infrastructure:security"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
