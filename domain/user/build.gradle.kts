plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    // infrastructure
    implementation(project(":infrastructure:jpa"))
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
