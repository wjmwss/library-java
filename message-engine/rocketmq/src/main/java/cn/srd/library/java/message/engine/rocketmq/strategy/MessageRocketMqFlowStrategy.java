// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.strategy;

import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageRocketMqFlowStrategy implements MessageFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        throw new UnsupportedOperationException();
    }

}