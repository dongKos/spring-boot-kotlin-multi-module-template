import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22" apply false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("io.github.microutils:kotlin-logging:3.0.4")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.springframework.boot:spring-boot-starter-test")

        // Logback
        implementation("ch.qos.logback.contrib:logback-json-classic:0.1.5")
        implementation("ch.qos.logback.contrib:logback-jackson:0.1.5")

        // open-api
        implementation("org.springdoc:springdoc-openapi-ui:1.6.12")
        implementation("org.springdoc:springdoc-openapi-kotlin:1.6.12")
    }
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

configure<AllOpenExtension> {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
