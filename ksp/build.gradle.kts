plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":lib-common"))
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.21-1.0.5")
}