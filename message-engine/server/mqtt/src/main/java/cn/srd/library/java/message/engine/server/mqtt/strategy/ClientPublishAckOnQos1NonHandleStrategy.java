package cn.srd.library.java.message.engine.server.mqtt.strategy;

import cn.srd.library.java.message.engine.server.mqtt.model.dto.RpcRequestDTO;

/**
 * @author wjm
 * @since 2025-01-07 22:21
 */
public class ClientPublishAckOnQos1NonHandleStrategy implements ClientPublishAckOnQos1Strategy {

    @Override
    public Void process(RpcRequestDTO rpcRequestDTO) {
        return null;
    }

}