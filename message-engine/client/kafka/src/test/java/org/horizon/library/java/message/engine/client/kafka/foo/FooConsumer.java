package org.horizon.library.java.message.engine.client.kafka.foo;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.library.java.message.engine.client.contract.MessageClientConsumer;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.library.java.message.engine.client.kafka.KafkaConfig;
import org.horizon.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooConsumer {

    @MessageClientConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void receive1(String message) {
        System.out.println(STR."消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void receive2(String message) {
        System.out.println(STR."消费者2 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "2")
            ))
    )
    public void receive3(String message) {
        System.out.println(STR."消费者3 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}