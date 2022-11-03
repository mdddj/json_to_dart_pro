import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "shop.itbug"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fifesoft:rsyntaxtextarea:3.3.0")
    implementation("com.alibaba:fastjson:2.0.17")
    implementation("io.ktor:ktor-client-websockets:2.1.2")
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-jetty:2.1.2")
    implementation("io.ktor:ktor-client-cio:2.1.2")
    implementation("org.hildan.krossbow:krossbow-stomp-core:4.4.0")
    implementation("org.hildan.krossbow:krossbow-websocket-ktor:4.4.0")
    implementation("org.hildan.krossbow:krossbow-websocket-okhttp:4.4.0")
    implementation("org.hildan.krossbow:krossbow-websocket-spring:4.4.0")
    implementation("org.hildan.krossbow:krossbow-websocket-sockjs:4.4.0")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:2.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("javax.websocket:javax.websocket-api:1.1")
    implementation("org.java-websocket:Java-WebSocket:1.5.3")



}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}