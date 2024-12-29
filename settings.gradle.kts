// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/spring/")
        maven("https://repo.spring.io/milestone/")
        maven("https://repo.spring.io/snapshot/")
        gradlePluginPortal()
    }
}

rootProject.name = "library-java"

System.setProperty("rootProjectAbsolutePath", rootDir.absolutePath)

include(":bom")

include(":cache:all")
include(":cache:caffeine")
include(":cache:contract")
include(":cache:lettuce")
include(":cache:map")
include(":cache:redis")

include(":cloud:communication:dromara-sms")
include(":cloud:communication:jakarta")
include(":cloud:native:docker")
include(":cloud:native:kubernetes")

include(":concurrent:actor")
include(":concurrent:redis")

include(":contract:component:redis")
include(":contract:component:oss")
include(":contract:constant")
include(":contract:model")

include(":doc:knife4j:contract")
include(":doc:knife4j:spring-gateway")
include(":doc:knife4j:spring-webmvc")
include(":doc:knife4j:spring-webflux")

include(":gateway:spring")

include(":jdbc:cassandra")
include(":jdbc:elasticsearch")
include(":jdbc:hsqldb")
include(":jdbc:mysql")
include(":jdbc:postgis")
include(":jdbc:postgresql")
include(":jdbc:tdengine")

include(":message-engine:client:all")
include(":message-engine:client:contract")
include(":message-engine:client:kafka")
include(":message-engine:client:mqtt:contract")
include(":message-engine:client:mqtt:v3")
include(":message-engine:client:mqtt:v5")
include(":message-engine:client:rabbitmq")
include(":message-engine:client:redis-stream")
include(":message-engine:client:rocketmq")

include(":metric:drop-wizard")
include(":metric:micrometer:prometheus")
include(":metric:spring")
include(":metric:system")

include(":orm:contract")
include(":orm:contract-mybatis-base")
include(":orm:contract-mybatis-base-postgis")
include(":orm:contract-mybatis-base-postgresql")
include(":orm:contract-mybatis-flex")
include(":orm:mybatis-flex-base")
include(":orm:mybatis-flex-postgis")
include(":orm:mybatis-flex-postgresql")
include(":orm:mybatis-flex-tdengine")
include(":orm:mybatis-plus")
include(":orm:spring-jdbc")
include(":orm:spring-jpa")

include(":oss:api")
include(":oss:contract")
include(":oss:local")
include(":oss:minio")

include(":pluggable-annotation-api:lombok")
include(":pluggable-annotation-api:mica-auto")
include(":pluggable-annotation-api:processor:jmh")
include(":pluggable-annotation-api:processor:lombok-mapstruct-binding")
include(":pluggable-annotation-api:processor:mapstruct-plus")
include(":pluggable-annotation-api:processor:mapstruct-protobuf")
include(":pluggable-annotation-api:processor:mybatis-flex")
include(":pluggable-annotation-api:processor:spring")

include(":pool:apache")
include(":pool:database:hikaricp")

include(":protocol:javax-sip")

include(":registration:nacos")
include(":registration:zookeeper")

include(":security:contract")
include(":security:spring")
include(":security:sa-token")

include(":studio:low-code")

include(":test:container")
include(":test:jmh")
include(":test:junit")
include(":test:mockito")
include(":test:spring-boot")
include(":test:spring-integration")

include(":tool:content-analysis")
include(":tool:convert:api")
include(":tool:convert:jackson")
include(":tool:convert:jackson-geometry")
include(":tool:convert:mapstruct-plus")
include(":tool:convert:mapstruct-protobuf")
include(":tool:convert:protobuf")
include(":tool:convert:spring")
include(":tool:enums")
include(":tool:excel")
include(":tool:geometry")
include(":tool:id:snowflake")
include(":tool:id:uuid")
include(":tool:jdk")
include(":tool:jna")
include(":tool:job:quartz")
include(":tool:lang")
include(":tool:log")
include(":tool:rate-limiting")
include(":tool:serialization:fastjson")
include(":tool:serialization:fastjson2")
include(":tool:serialization:fst")
include(":tool:serialization:jackson")
include(":tool:serialization:protobuf:google")
include(":tool:serialization:protobuf:squareup")
include(":tool:spring:contract")
include(":tool:spring:webflux")
include(":tool:spring:webmvc")
include(":tool:template-engine")
include(":tool:thread-pool:contract")
include(":tool:thread-pool:config:nacos")
include(":tool:thread-pool:integration:grpc")
include(":tool:thread-pool:integration:okhttp3")
include(":tool:thread-pool:integration:tomcat")
include(":tool:validation:hibernate")
include(":tool:validation:jakarta")
include(":tool:validation:json-schema")
include(":tool:validation:spring")
include(":tool:xml")

include(":transaction:spring")

include(":web:grpc:client")
include(":web:grpc:contract")
include(":web:grpc:server")
include(":web:http:okhttps")
include(":web:netty")
include(":web:openfeign")
include(":web:reactor")
include(":web:websocket")