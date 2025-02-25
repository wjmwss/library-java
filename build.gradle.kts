plugins {
    id(GradlePlugin.JAVA_LIBRARY)
    id(GradlePlugin.MAVEN_PUBLISH)
    id(GradlePlugin.CHECK_STYLE)
    signing
//    id ("com.vanniktech.maven.publish") version ("0.30.0")
}

allprojects {
    apply(plugin = GradlePlugin.MAVEN_PUBLISH)
    apply(plugin = "signing")
//    apply(plugin = "com.vanniktech.maven.publish")
    apply(plugin = GradlePlugin.CHECK_STYLE)

    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/spring/")
        maven("https://repo.spring.io/milestone/")
        maven("https://repo.spring.io/snapshot/")
        mavenCentral()
    }

    checkstyle {
        toolVersion = GradlePlugin.CHECK_STYLE_VERSION
        configFile = file("$rootDir" + GradlePlugin.CHECK_STYLE_CONFIG_PATH)
    }

    tasks.withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<JavaCompile> {
        options.release = Integer.valueOf(GradleConfig.JAVA_VERSION)
        options.encoding = GradleConfig.PROJECT_CHARSET
        options.compilerArgs = listOf(
            GradleConfig.WITH_PARAMETERS_ARG,
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            // "-Xlint:all",
        )
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs(
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            GradleConfig.WITH_ENABLE_DYNAMIC_AGENT_LOADING,
        )
    }

    tasks.withType<JavaExec> {
        jvmArgs(
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            GradleConfig.WITH_ENABLE_DYNAMIC_AGENT_LOADING,
        )
    }

    tasks.withType<GenerateModuleMetadata> {
        suppressedValidationErrors.add("enforced-platform")
    }
}

subprojects {
    if (GradleModule.toModuleName(project.toString()) != GradleModule.BOM) {
        apply(plugin = GradlePlugin.JAVA_LIBRARY)

        dependencies {
            api(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))
            annotationProcessor(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))
            testAnnotationProcessor(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))

            if (GradleModule.toModuleName(project.toString()) != GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK) {
                compileOnly(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK)))
                annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK)))
                testCompileOnly(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK)))
                testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK)))
            }

            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JMH)))
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JUNIT)))
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_LOG)))
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING_BOOT)))
            testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH)))
            testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING)))
        }

        java {
            toolchain {
                vendor = JvmVendorSpec.AMAZON
                languageVersion = JavaLanguageVersion.of(GradleConfig.JAVA_VERSION)
            }
            // withJavadocJar()
            withSourcesJar()
            modularity.inferModulePath = true
        }

        sourceSets {
            main {
                resources {
                    setSrcDirs(listOf("src/main/java", "src/main/resources"))
                }
            }
        }

        publishing {
            publications {
                create<MavenPublication>(GradleRepository.REPOSITORY_DEFAULT_NAME) {
                    from(components[GradleRepository.COMPONENT_JAVA])
                    groupId = GradleRepository.GROUP_ID
                    artifactId = GradleModule.toModuleName(project.toString())
                    version = GradleConfig.PROJECT_VERSION

                    pom {
                        name.set(GradleModule.toModuleName(project.toString()))
                        description.set("A description of what my library does.")
                        inceptionYear.set("2022")
                        url.set("https://gitee.com/wjmwss/library-java")
                        licenses {
                            license {
                                name.set("The Apache License, Version 2.0")
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                        developers {
                            developer {
                                id.set("jimmy")
                                name.set("jimmy")
                                email.set("wjmwss@outlook.com")
                                url.set("https://gitee.com/wjmwss")
                            }
                        }
                        scm {
                            url.set("https://gitee.com/wjmwss/library-java")
                            connection.set("scm:git:https://gitee.com/wjmwss/library-java.git")
                            developerConnection.set("scm:git:ssh://https://gitee.com/wjmwss/library-java.git")
                        }
                    }
                }
            }

            repositories {
                maven {
                    url = uri("https://central.sonatype.com/")
                    credentials {
                        username = project.findProperty("mavenCentralUsername") as String?
                        password = project.findProperty("mavenCentralPassword") as String?
                    }
                }
            }
        }

        signing {
//            useGpgCmd()
            sign(publishing.publications["Maven"])
        }

//        mavenPublishing {
//            configure(com.vanniktech.maven.publish.JavaLibrary(
//                sourcesJar = true,
//                javadocJar = com.vanniktech.maven.publish.JavadocJar.Empty(),
//            ))
//            publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
//            signAllPublications()
//
//////            group = GradleRepository.GROUP_ID
////            artifacts{
////                add("archives", tasks.jar)
////            }
////            version = GradleConfig.PROJECT_VERSION
//
//            pom {
//                group = GradleRepository.GROUP_ID
//                artifacts {
//                    name.set(GradleModule.toModuleName(project.toString()))
//                }
//                version = GradleConfig.PROJECT_VERSION
//                name.set(GradleModule.toModuleName(project.toString()))
//                description.set("A description of what my library does.")
//                inceptionYear.set("2022")
//                url.set("https://gitee.com/wjmwss/library-java")
//                licenses {
//                    license {
//                        name.set("The Apache License, Version 2.0")
//                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                    }
//                }
//                developers {
//                    developer {
//                        id.set("jimmy")
//                        name.set("jimmy")
//                        email.set("wjmwss@outlook.com")
//                        url.set("https://gitee.com/wjmwss")
//                    }
//                }
//                scm {
//                    url.set("https://gitee.com/wjmwss/library-java")
//                    connection.set("scm:git:https://gitee.com/wjmwss/library-java.git")
//                    developerConnection.set("scm:git:ssh://https://gitee.com/wjmwss/library-java.git")
//                }
//            }
//        }

        tasks.processResources {
            filesMatching("**/*.yaml") {
                expand(
                    GradleConfig.ACTIVE_ENVIRONMENT_FIELD_NAME to GradleConfig.activeEnvironmentName,
                )
            }
        }

        tasks.processTestResources {
            filesMatching("**/*.yaml") {
                expand(
                    GradleConfig.ACTIVE_ENVIRONMENT_FIELD_NAME to GradleConfig.activeEnvironmentName,
                )
            }
        }
    }
}