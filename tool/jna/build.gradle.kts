dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_JNA.withoutVersion)
    api(GradleDependency.TOOL_JNA_JPMS.withoutVersion)
}