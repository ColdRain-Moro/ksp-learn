plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
    idea
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":lib-common"))
    ksp(project(":ksp"))
}

idea {
    module {
        // Not using += due to https://github.com/gradle/gradle/issues/8749
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin") // or tasks["kspKotlin"].destination
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs = generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}

sourceSets {
    main {
        java {
            srcDirs += file("build/generated/ksp/main/kotlin")
        }
    }
}