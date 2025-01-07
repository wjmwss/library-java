package cn.srd.library.java.message.engine.server.mqtt.autoconfigure;

import cn.srd.library.java.message.engine.server.mqtt.context.MqttServerContext;
import cn.srd.library.java.message.engine.server.mqtt.model.property.MqttServerCommonProperty;
import cn.srd.library.java.message.engine.server.mqtt.model.property.MqttServerNonSslProperty;
import cn.srd.library.java.message.engine.server.mqtt.model.property.MqttServerSslProperty;
import cn.srd.library.java.message.engine.server.mqtt.strategy.*;
import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static cn.srd.library.java.message.engine.contract.autoconfigure.MessageEngineBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Server MQTT
 *
 * @author wjm
 * @since 2024-12-29 17:23
 */
@Slf4j
@AutoConfiguration
@ConditionalOnBean(MessageMqttServerRegistrar.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEnumAutowired(scanPackagePaths = BASE_PACKAGE_PATH)
@EnableConfigurationProperties({MqttServerCommonProperty.class, MqttServerNonSslProperty.class, MqttServerSslProperty.class})
public class MqttServerAutoConfigurer {

    @Bean
    public MqttServerLifecycleManager mqttServerLifecycleManager() {
        return new MqttServerLifecycleManager();
    }

    @Bean
    public MqttServerContext mqttServerContext() {
        return new MqttServerContext();
    }

    @Bean
    public MqttMessageConnectStrategy mqttMessageConnectStrategy() {
        return new MqttMessageConnectStrategy();
    }

    @Bean
    public MqttMessagePublishStrategy mqttMessagePublishStrategy() {
        return new MqttMessagePublishStrategy();
    }

    @Bean
    public MqttMessagePublishAckOnQos1Strategy mqttMessagePublishAckOnQos1Strategy() {
        return new MqttMessagePublishAckOnQos1Strategy();
    }

    @Bean
    public MqttMessageSubscribeStrategy mqttMessageSubscribeStrategy() {
        return new MqttMessageSubscribeStrategy();
    }

    @Bean
    public MqttMessageUnsubscribeStrategy mqttMessageUnsubscribeStrategy() {
        return new MqttMessageUnsubscribeStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientConnectAuthStrategy.class)
    public ClientConnectNonAuthStrategy clientConnectNonAuthStrategy() {
        return new ClientConnectNonAuthStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientPublishStrategy.class)
    public ClientPublishNonHandleStrategy clientPublishNonHandleStrategy() {
        return new ClientPublishNonHandleStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientPublishAckOnQos1Strategy.class)
    public ClientPublishAckOnQos1NonHandleStrategy clientPublishAckOnQos1NonHandleStrategy() {
        return new ClientPublishAckOnQos1NonHandleStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientSubscribeStrategy.class)
    public ClientSubscribeNonHandleStrategy clientSubscribeNonHandleStrategy() {
        return new ClientSubscribeNonHandleStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(ClientUnsubscribeStrategy.class)
    public ClientUnsubscribeNonHandleStrategy clientUnsubscribeNonHandleStrategy() {
        return new ClientUnsubscribeNonHandleStrategy();
    }

}