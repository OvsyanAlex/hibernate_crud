plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.liquibase:liquibase-core:4.31.1")
    implementation("org.hibernate:hibernate-core:6.4.4.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
}