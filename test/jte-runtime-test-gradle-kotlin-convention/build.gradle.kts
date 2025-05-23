
plugins {
    id("java")
    id("gg.jte.gradle").version("3.2.2-SNAPSHOT")
}

repositories {
    mavenCentral()
    mavenLocal()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation("gg.jte:jte-runtime:3.2.2-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

jte {
    precompile()
}

tasks.jar {
    dependsOn(tasks.precompileJte)
    from(fileTree("jte-classes") {
        include("**/*.class")
    })
}