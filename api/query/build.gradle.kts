dependencies {
    // domain
    implementation(project(":domain:user"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
