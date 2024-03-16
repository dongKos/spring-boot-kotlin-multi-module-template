plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}
dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.hibernate:hibernate-core:5.4.27.Final")
    api("com.mysql:mysql-connector-j:8.2.0")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
