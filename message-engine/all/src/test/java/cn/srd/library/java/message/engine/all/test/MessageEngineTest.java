// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.all.test;

import cn.srd.library.java.message.engine.all.foo.FooProducer;
import cn.srd.library.java.message.engine.kafka.autoconfigure.EnableMessageEngineKafka;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.EnableMessageEngineMqttV3;
import cn.srd.library.java.message.engine.mqtt.v5.autoconfigure.EnableMessageEngineMqttV5;
import cn.srd.library.java.message.engine.rabbitmq.autoconfigure.EnableMessageEngineRabbitMq;
import cn.srd.library.java.message.engine.redis.autoconfigure.EnableMessageEngineRedis;
import cn.srd.library.java.message.engine.rocketmq.autoconfigure.EnableMessageEngineRocketMq;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

/**
 * kafka test
 *
 * @author wjm
 * @since 2024-05-21 21:55
 */
@EnableMessageEngineKafka
@EnableMessageEngineMqttV3
@EnableMessageEngineMqttV5
@EnableMessageEngineRabbitMq
@EnableMessageEngineRedis
@EnableMessageEngineRocketMq
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringIntegrationTest
class MessageEngineTest {

    @Autowired private FooProducer fooProducer;

    @SneakyThrows
    @Test
    void test() {
        while (true) {
            fooProducer.kafkaSend1();
            fooProducer.kafkaSend2();
            fooProducer.mqttV3Send1();
            fooProducer.mqttV3Send2();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}