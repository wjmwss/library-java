dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_VALIDATION_JSON_SCHEMA.withoutVersion)
}