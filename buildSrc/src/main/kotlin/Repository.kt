object GradleRepository {

    const val GROUP_ID = "io.github.wjmwss"

    const val REPOSITORY_DEFAULT_NAME = "Maven"

    const val COMPONENT_JAVA = "java"
    const val COMPONENT_JAVA_PLATFORM = "javaPlatform"

    val nexusUrl = GradleConfig.activeEnvironment.handler.getNexusUrl()
    const val NEXUS_USERNAME = "WMnhm09y"
    const val NEXUS_PASSWORD = "DWrT2+t2Vifpjxu0h+z/litOVE8eA9hq5GCQVl2ygwo4"
    const val NEXUS_LOCALHOST_URL = "http://localhost:10015/repository/library-java/"
    const val NEXUS_DEVELOPMENT_URL = "http://192.168.10.91:32122/repository/library-java/"
    const val NEXUS_PRODUCTION_URL = "http://192.168.10.86:32208/repository/library-java/"

}