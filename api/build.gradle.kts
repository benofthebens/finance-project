plugins {
    id("java")
    id("io.spring.dependency-management") version("1.1.6")
}

group = "org.financeproject"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.2"))
    implementation(project(":utils"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}