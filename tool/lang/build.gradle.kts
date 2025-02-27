dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_CONSTANT)))
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_MODEL)))

    api(GradleDependency.TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL.withoutVersion)
    api(GradleDependency.TOOL_GOOGLE_GUAVA.withoutVersion)
    api(GradleDependency.TOOL_HUTOOL.withoutVersion)
    api(GradleDependency.TOOL_IO_VAVR.withoutVersion)
    api(GradleDependency.TOOL_REFLECT_ASM.withoutVersion)
    api(GradleDependency.TOOL_REFLECT_RONMAMO.withoutVersion)
    api(GradleDependency.TOOL_STREAM_JDFRAME.withoutVersion)
    api(GradleDependency.TOOL_VJTOOL.withoutVersion)
}