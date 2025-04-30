plugins {
    id("conventions-plugin.java")
    id("org.springframework.boot")
    id("io.freefair.lombok")
}

val libs = extensions.getByType<VersionCatalogsExtension>()
    .named("libs")


dependencies {
    implementation(platform(libs.findLibrary("spring-boot-dependencies").get()))
    implementation(platform(libs.findLibrary("spring-cloud-dependencies").get()))
}