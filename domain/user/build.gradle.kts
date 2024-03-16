dependencies {
    implementation(project(":infrastructure:jpa"))
    implementation(project(":common:util"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
