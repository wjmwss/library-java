package org.horizon.library.java.message.engine.server.mqtt.handler;

import org.horizon.library.java.message.engine.server.mqtt.model.dto.RpcRequestDTO;

/**
 * @author wjm
 * @since 2025-01-07 22:21
 */
public interface ClientPublishAckOnQualityOfService1Handler {

    Void process(RpcRequestDTO rpcRequestDTO);

}