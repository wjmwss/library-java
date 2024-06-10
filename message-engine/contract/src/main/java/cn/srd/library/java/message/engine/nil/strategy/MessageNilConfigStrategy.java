// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.nil.strategy;

import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.nil.model.dto.MessageNilConfigDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageNilConfigStrategy extends MessageConfigStrategy<MessageNilConfigDTO, MessageNilConfigDTO.BrokerDTO, MessageNilConfigDTO.ClientDTO, MessageNilConfigDTO.ProducerDTO, MessageNilConfigDTO.ConsumerDTO> {

    @Override
    protected Class<MessageNilConfigDTO> getConfigType() {
        return MessageNilConfigDTO.class;
    }

    @Override
    protected MessageNilConfigDTO.BrokerDTO getBrokerDTO() {
        return null;
    }

    @Override
    protected void registerClientFactory(MessageNilConfigDTO.BrokerDTO brokerDTO) {

    }

    @Override
    protected MessageNilConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        return null;
    }

    @Override
    protected void registerProducerFlow(MessageNilConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(MessageNilConfigDTO.ConsumerDTO consumerDTO) {

    }

    @Override
    protected void registerConsumerFlow(MessageNilConfigDTO.ConsumerDTO consumerDTO) {

    }

    @Override
    protected MessageNilConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        return null;
    }

    @Override
    protected MessageNilConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        return null;
    }

}