// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable message engine kafka system.
 *
 * @author wjm
 * @see MessageEngineKafkaSwitcher
 * @see MessageEngineKafkaAutoConfigurer#messageKafkaConfigDO()
 * @since 2024-05-24 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MessageEngineKafkaSwitcher.class)
public @interface EnableMessageEngineKafka {

}