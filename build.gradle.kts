plugins {
	id("conventions-plugin.spring")
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.data.jpa)
	implementation(libs.spring.boot.starter.validation)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.springdoc.openapi.starter.webmvc.ui)
	implementation(libs.spring.boot.configuration.processor)
	implementation(libs.jjwt)
	implementation(libs.liquibase.core)
	implementation(libs.mapstruct)
	runtimeOnly(libs.driver.postgresql)
	annotationProcessor(libs.mapstruct.processor)
}