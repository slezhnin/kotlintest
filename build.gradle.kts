import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.70"
    `maven-publish`
}

group = "com.lezhnin.project"
version = "1.0-SNAPSHOT"
description = "com.lezhnin.project kotlintest"

repositories {
    jcenter()
    mavenLocal()
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        register("maven", MavenPublication::class) {
            from(components["java"])
        }
    }
}

dependencies {
    compile(kotlin("stdlib"))
    compile("io.kotlintest:kotlintest-assertions:3.1.9")
    testCompile("io.kotlintest:kotlintest-runner-junit5:3.1.9")
}

tasks {
    withType<Test> {
        useJUnitPlatform {
            includeEngines("junit-jupiter")
        }
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    withType<Jar> {
        manifest {
            attributes["Specification-Title"] = project.name
            attributes["Specification-Version"] = project.version
            attributes["Specification-Vendor"] = project.group
        }
    }
}
