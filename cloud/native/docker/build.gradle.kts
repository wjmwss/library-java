// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.CLOUD_NATIVE_DOCKER_CORE.withoutVersion)
    api(GradleDependency.CLOUD_NATIVE_DOCKER_TRANSPORT_OKHTTP.withoutVersion)
}