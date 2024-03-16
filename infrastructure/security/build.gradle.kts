dependencies {
    api("org.springframework.boot:spring-boot-starter-security")
    api("com.auth0:java-jwt:3.10.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // common
    implementation(project(":common:util"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
