// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3;

import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.message.engine.contract.MessageEngineAction;
import cn.srd.library.java.message.engine.contract.MessageSend;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineMqttAction implements MessageEngineAction {

    @Autowired private MqttPahoClientFactory mqttClientFactory;

    @Autowired private IntegrationFlowContext flowContext;

    @Override
    public MessageEngineMqttAction registerSendFlowIfNeed(String flowId, MessageSend messageSendAnnotation) {
        if (Nil.isNull(flowContext.getRegistrationById(flowId))) {
            MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher3", this.mqttClientFactory);
            messageHandler.setAsync(true);
            messageHandler.setDefaultTopic("siSampleTopic");
            messageHandler.setDefaultQos(1);
            messageHandler.setCompletionTimeout(5000);
            this.flowContext
                    .registration(flow -> flow.transform(message -> Converts.withJackson().toString(message)).handle(messageHandler))
                    .id(flowId)
                    .useFlowIdAsPrefix()
                    .register();
        }
        return this;
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        return this.flowContext
                .getRegistrationById(flowId)
                .getInputChannel()
                .send(new GenericMessage<>(MessageModel.builder().status(200).message("ok").data(message).build()));
    }

}