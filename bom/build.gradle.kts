plugins {
    id(GradlePlugin.JAVA_PLATFORM)
}

dependencies {
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_CLOUD_NATIVE_DOCKER)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_DOC_SPRING_OPENAPI)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_DOC_XIAOYMIN_KNIFE4J)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ECLIPSE_PAHO)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_ALIBABA_SPRING_CLOUD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_BOOT)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_CLOUD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_INTEGRATION)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_JDBC_POSTGIS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_METRIC_DROPWIZARD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_METRIC_MICROMETER)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ORM_MYBATIS_FLEX)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ORM_MYBATIS_PLUS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_OSS_ALL_FACED)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_SECURITY_DEV33_SA_TOKEN)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TEST_MOCKITO)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TEST_TESTCONTAINERS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_DYNAMIC_TP)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_GEOMETRY_JTS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_MAPSTRUCT_PLUS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_MAPSTRUCT_PROTOBUF)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_FASTERXML_JACKSON)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_GOOGLE_PROTOBUF)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_SQUAREUP_WIRE)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_FEIGN)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_GRPC)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_NETTY)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_REACTOR)))

    constraints {
//        rootProject.allprojects
//            .filterNot { it.path == ":" }
//            .forEach { api(project(it.path)) }

        api(GradleDependency.withVersion(GradleDependency.CACHE_CAFFEINE))
        api(GradleDependency.withVersion(GradleDependency.CACHE_LETTUCE))
        api(GradleDependency.withVersion(GradleDependency.CACHE_REDISSON))

        api(GradleDependency.withVersion(GradleDependency.CLOUD_COMMUNICATION_DROMARA_SMS))
        api(GradleDependency.withVersion(GradleDependency.CLOUD_COMMUNICATION_JAKARTA_MAIL))

        api(GradleDependency.withVersion(GradleDependency.JDBC_HSQLDB))
        api(GradleDependency.withVersion(GradleDependency.JDBC_MYSQL))
        api(GradleDependency.withVersion(GradleDependency.JDBC_POSTGIS))
        api(GradleDependency.withVersion(GradleDependency.JDBC_POSTGRESQL))
        api(GradleDependency.withVersion(GradleDependency.JDBC_TDENGINE))

        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS))
        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS_PLUS))
        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE))
        api(GradleDependency.withVersion(GradleDependency.ORM_SPRING_INTEGRATION_CASSANDRA))

        api(GradleDependency.withVersion(GradleDependency.OSS_ALL_FACED))
        api(GradleDependency.withVersion(GradleDependency.OSS_MINIO))

        api(GradleDependency.withVersion(GradleDependency.MESSAGE_MQTT_V5_ECLIPSE_PAHO))
        api(GradleDependency.withVersion(GradleDependency.MESSAGE_ROCKETMQ_TODO)) // TODO wjm need to remove

        api(GradleDependency.withVersion(GradleDependency.METRIC_BIT_WALKER_USER_AGENT))
        api(GradleDependency.withVersion(GradleDependency.METRIC_OSHI_CORE))

        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PLUS))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PROTOBUF))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MICA_AUTO))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION))

        api(GradleDependency.withVersion(GradleDependency.POOL_APACHE))
        api(GradleDependency.withVersion(GradleDependency.POOL_DATABASE_HIKARICP))

        api(GradleDependency.withVersion(GradleDependency.PROTOCOL_JAVAX_SIP))

        api(GradleDependency.withVersion(GradleDependency.SECURITY_PASSAY))

        api(GradleDependency.withVersion(GradleDependency.TOOL_ALIBABA_EASY_EXCEL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_ANNOTATION_API_JAVAX))
        api(GradleDependency.withVersion(GradleDependency.TOOL_AUTOWIRED_SMART_SPRING))
        api(GradleDependency.withVersion(GradleDependency.TOOL_CONTENT_ANALYSIS_APACHE_TIKA_PARSER))
        api(GradleDependency.withVersion(GradleDependency.TOOL_TEMPLATE_ENGINE_APACHE_VELOCITY))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_JTS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GOOGLE_GUAVA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_HUTOOL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_IO_VAVR))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JDK_BURNING_WAVE_CORE))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JNA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JNA_JPMS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JOB_QUARTZ))
        api(GradleDependency.withVersion(GradleDependency.TOOL_MAPSTRUCT_PLUS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_REFLECT_ASM))
        api(GradleDependency.withVersion(GradleDependency.TOOL_REFLECT_RONMAMO))
        api(GradleDependency.withVersion(GradleDependency.TOOL_RATE_LIMITING_BUCKET4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FASTJSON))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FASTJSON2))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FST))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC))
        api(GradleDependency.withVersion(GradleDependency.TOOL_STREAM_JDFRAME))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_HIBERNATE))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_JAKARTA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_JSON_SCHEMA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VJTOOL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_XML_DOM4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_XML_JAVAX_JAXB_API))
        api(GradleDependency.withVersion(GradleDependency.TOOL_YITTER_SNOWFLAKE_ID))

        api(GradleDependency.withVersion(GradleDependency.TEST_JUNIT))
        api(GradleDependency.withVersion(GradleDependency.TEST_OPENJDK_JMH_CORE))

        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_SPRING_BOOT_SERVER))
        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_SPRING_BOOT_CLIENT))
        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_PROTO_GEN))
        api(GradleDependency.withVersion(GradleDependency.WEB_OKHTTPS))

        api(project(GradleModule.toReferenceName(GradleModule.BOM)))

        api(project(GradleModule.toReferenceName(GradleModule.CACHE_ALL)))
        api(project(GradleModule.toReferenceName(GradleModule.CACHE_CAFFEINE)))
        api(project(GradleModule.toReferenceName(GradleModule.CACHE_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.CACHE_LETTUCE)))
        api(project(GradleModule.toReferenceName(GradleModule.CACHE_MAP)))
        api(project(GradleModule.toReferenceName(GradleModule.CACHE_REDIS)))

        api(project(GradleModule.toReferenceName(GradleModule.CLOUD_COMMUNICATION_DROMARA_SMS)))
        api(project(GradleModule.toReferenceName(GradleModule.CLOUD_COMMUNICATION_JAKARTA)))
        api(project(GradleModule.toReferenceName(GradleModule.CLOUD_NATIVE_DOCKER)))
        api(project(GradleModule.toReferenceName(GradleModule.CLOUD_NATIVE_KUBERNETES)))

        api(project(GradleModule.toReferenceName(GradleModule.CONCURRENT_ACTOR)))
        api(project(GradleModule.toReferenceName(GradleModule.CONCURRENT_REDIS)))

        api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_COMPONENT_REDIS)))
        api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_COMPONENT_OSS)))
        api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_CONSTANT)))
        api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_MODEL)))

        api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_SPRING_GATEWAY)))
        api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_SPRING_WEBFLUX)))
        api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_SPRING_WEBMVC)))

        api(project(GradleModule.toReferenceName(GradleModule.GATEWAY_SPRING)))

        api(project(GradleModule.toReferenceName(GradleModule.JDBC_CASSANDRA)))
        api(project(GradleModule.toReferenceName(GradleModule.JDBC_ELASTICSEARCH)))
        api(project(GradleModule.toReferenceName(GradleModule.JDBC_HSQLDB)))
        api(project(GradleModule.toReferenceName(GradleModule.JDBC_MYSQL)))
        api(project(GradleModule.toReferenceName(GradleModule.JDBC_POSTGRESQL)))
        api(project(GradleModule.toReferenceName(GradleModule.JDBC_TDENGINE)))

        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_ALL)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_KAFKA)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_V3)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_MQTT_V5)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_RABBITMQ)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_REDIS_STREAM)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_ROCKETMQ)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_SERVER_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_SERVER_MQTT)))

        api(project(GradleModule.toReferenceName(GradleModule.METRIC_DROP_WIZARD)))
        api(project(GradleModule.toReferenceName(GradleModule.METRIC_MICROMETER_PROMETHEUS)))
        api(project(GradleModule.toReferenceName(GradleModule.METRIC_SPRING)))
        api(project(GradleModule.toReferenceName(GradleModule.METRIC_SYSTEM)))

        api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE_POSTGIS)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE_POSTGRESQL)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_FLEX)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_BASE)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_POSTGIS)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_TDENGINE)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_PLUS)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_SPRING_JDBC)))
        api(project(GradleModule.toReferenceName(GradleModule.ORM_SPRING_JPA)))

        api(project(GradleModule.toReferenceName(GradleModule.OSS_API)))
        api(project(GradleModule.toReferenceName(GradleModule.OSS_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.OSS_LOCAL)))
        api(project(GradleModule.toReferenceName(GradleModule.OSS_MINIO)))

        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_MICA_AUTO)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PROTOBUF)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
        api(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING)))

        api(project(GradleModule.toReferenceName(GradleModule.POOL_APACHE)))
        api(project(GradleModule.toReferenceName(GradleModule.POOL_DATABASE_HIKARICP)))

        api(project(GradleModule.toReferenceName(GradleModule.PROTOCOL_JAVAX_SIP)))

        api(project(GradleModule.toReferenceName(GradleModule.REGISTRATION_NACOS)))
        api(project(GradleModule.toReferenceName(GradleModule.REGISTRATION_ZOOKEEPER)))

        api(project(GradleModule.toReferenceName(GradleModule.SECURITY_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.SECURITY_SPRING)))
        api(project(GradleModule.toReferenceName(GradleModule.SECURITY_SA_TOKEN)))

        api(project(GradleModule.toReferenceName(GradleModule.STUDIO_LOW_CODE)))

        api(project(GradleModule.toReferenceName(GradleModule.TEST_CONTAINER)))
        api(project(GradleModule.toReferenceName(GradleModule.TEST_JMH)))
        api(project(GradleModule.toReferenceName(GradleModule.TEST_JUNIT)))
        api(project(GradleModule.toReferenceName(GradleModule.TEST_MOCKITO)))
        api(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_BOOT)))
        api(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_INTEGRATION)))

        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONTENT_ANALYSIS)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON_GEOMETRY)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_MAPSTRUCT_PLUS)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_MAPSTRUCT_PROTOBUF)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_PROTOBUF)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_SPRING)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_EXCEL)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_GEOMETRY)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_SNOWFLAKE)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_ID_UUID)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_JDK)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_JNA)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_JOB_QUARTZ)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_LOG)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_RATE_LIMITING)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_FASTJSON)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_FASTJSON2)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_FST)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_JACKSON)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_PROTOBUF_GOOGLE)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SERIALIZATION_PROTOBUF_SQUAREUP)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBFLUX)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBMVC)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_TEMPLATE_ENGINE)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_CONFIG_NACOS)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_INTEGRATION_GRPC)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_INTEGRATION_OKHTTP3)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_THREAD_POOL_INTEGRATION_TOMCAT)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_HIBERNATE)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_JAKARTA)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_JSON_SCHEMA)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_VALIDATION_SPRING_BOOT)))
        api(project(GradleModule.toReferenceName(GradleModule.TOOL_XML)))

        api(project(GradleModule.toReferenceName(GradleModule.TRANSACTION_SPRING)))

        api(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CLIENT)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_CONTRACT)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_GRPC_SERVER)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_HTTP_OKHTTPS)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_OPEN_FEIGN)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_REACTOR)))
        api(project(GradleModule.toReferenceName(GradleModule.WEB_WEBSOCKET)))
    }
}

javaPlatform {
    allowDependencies()
}

//publishing {
//    publications {
//        create<MavenPublication>(GradleRepository.REPOSITORY_DEFAULT_NAME) {
//            from(components[GradleRepository.COMPONENT_JAVA_PLATFORM])
//            groupId = GradleRepository.GROUP_ID
//            artifactId = GradleModule.BOM
//            version = GradleConfig.PROJECT_VERSION
//        }
//        repositories {
//            maven {
//                isAllowInsecureProtocol = true
//                url = uri(GradleRepository.nexusUrl)
//                credentials { username = GradleRepository.NEXUS_USERNAME }
//                credentials { password = GradleRepository.NEXUS_PASSWORD }
//            }
//        }
//    }
//}


//mavenPublishing {
//    configure(com.vanniktech.maven.publish.JavaPlatform())
//    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
//    signAllPublications()
//
//    pom {
////        group = GradleRepository.GROUP_ID
////        version = GradleConfig.PROJECT_VERSION
//        name.set(GradleModule.toModuleName(project.toString()))
//        description.set("A description of what my library does.")
//        inceptionYear.set("2022")
//        url.set("https://gitee.com/wjmwss/library-java")
//        licenses {
//            license {
//                name.set("The Apache License, Version 2.0")
//                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//            }
//        }
//        developers {
//            developer {
//                id.set("jimmy")
//                name.set("jimmy")
//                email.set("wjmwss@outlook.com")
//                url.set("https://gitee.com/wjmwss")
//            }
//        }
//        scm {
//            url.set("https://gitee.com/wjmwss/library-java")
//            connection.set("scm:git:https://gitee.com/wjmwss/library-java.git")
//            developerConnection.set("scm:git:ssh://https://gitee.com/wjmwss/library-java.git")
//        }
//    }
//}