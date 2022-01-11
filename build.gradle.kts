plugins {
    kotlin("jvm")
}

group = "com.yrc"
//version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.springframework:spring-jcl:5.3.14")
}