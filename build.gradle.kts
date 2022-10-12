
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("idea")
    kotlin("kapt") version "1.4.10"
}

group = "org.jackie35er"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenCentral()
}

idea {
    module {
        sourceDirs.plusAssign(file("generated/"))
        generatedSourceDirs.plusAssign(file("generated/"))
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation ("com.querydsl:querydsl-sql:5.0.0")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:general")


    annotationProcessor("javax.annotation:javax.annotation-api:1.3.2")

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
