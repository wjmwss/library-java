dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.FRAMEWORK_SPRING_BOOT_WEBFLUX.withoutVersion)
}