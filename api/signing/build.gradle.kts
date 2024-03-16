dependencies {
    // domain
    implementation(project(":domain:user"))

    // infrastructure
    implementation(project(":infrastructure:security"))

    // common
    implementation(project(":common:util"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
