dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.CLOUD_COMMUNICATION_DROMARA_SMS.withoutVersion)
}