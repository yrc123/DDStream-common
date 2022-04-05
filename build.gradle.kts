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
    implementation("org.valiktor:valiktor-core:0.12.0")
    //hash
    implementation("commons-codec:commons-codec:1.15")
    //jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    //jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("com.baomidou:mybatis-plus-core:3.5.0")
    implementation("com.baomidou:mybatis-plus-extension:3.5.0")
    compileOnly("com.zaxxer:nuprocess:2.0.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}