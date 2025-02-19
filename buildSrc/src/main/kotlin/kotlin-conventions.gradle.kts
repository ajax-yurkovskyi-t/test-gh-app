import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm")
    jacoco
    `java-test-fixtures`
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
}

val targetJvmVersion = JavaLanguageVersion.of(21)
kotlin {
    jvmToolchain {
        languageVersion.set(targetJvmVersion)
    }
}

java {
    toolchain {
        languageVersion.set(targetJvmVersion)
    }
}

tasks.withType<Detekt> {
    reports {
        html.required.set(true)
        xml.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    named("testFixturesImplementation") {
        extendsFrom(configurations.implementation.get())
    }
}
