package cn.srd.library.java.message.engine.server.mqtt.context;

import cn.srd.library.java.message.engine.server.mqtt.matcher.MqttTopicMatcher;
import cn.srd.library.java.message.engine.server.mqtt.model.dto.RpcRequestDTO;
import cn.srd.library.java.tool.lang.collection.Collections;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

/**
 * @author wjm
 * @since 2025-01-05 22:19
 */
@Getter
public abstract class MqttClientAwareSessionContext extends ClientAwareSessionContext {

    private final Map<MqttTopicMatcher, MqttQoS> topicMappingSupportedQosMap = Collections.newConcurrentHashMap();

    private final Map<Integer, RpcRequestDTO> rpcAwaitingAckMap = Collections.newConcurrentHashMap();

    protected MqttClientAwareSessionContext(UUID sessionId) {
        super(sessionId);
    }

    public MqttQoS getQosForTopic(String topic) {
        return topicMappingSupportedQosMap.entrySet().stream()
                .filter(entry -> entry.getKey().match(topic))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(MqttQoS.AT_LEAST_ONCE);
    }

}