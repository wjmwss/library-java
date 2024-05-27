// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.consumer;

import cn.srd.library.java.message.engine.contract.MessageEngineType;
import cn.srd.library.java.message.engine.contract.MessageReceive;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    @MessageReceive(type = MessageEngineType.MQTT_V3)
    public String receive(String a) {
        System.out.println(a);
        return "foo";
    }

}