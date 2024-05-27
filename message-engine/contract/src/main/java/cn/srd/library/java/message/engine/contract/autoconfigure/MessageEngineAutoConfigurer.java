// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.autoconfigure;

import cn.srd.library.java.message.engine.contract.MessageProducerAspect;
import cn.srd.library.java.tool.enums.autowired.EnableEnumAutowired;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import static cn.srd.library.java.message.engine.contract.autoconfigure.MessageEngineBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableEnumAutowired(scanPackagePaths = BASE_PACKAGE_PATH)
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
public class MessageEngineAutoConfigurer {

    @Bean
    public MessageProducerAspect messageSendAspect() {
        return new MessageProducerAspect();
    }

}