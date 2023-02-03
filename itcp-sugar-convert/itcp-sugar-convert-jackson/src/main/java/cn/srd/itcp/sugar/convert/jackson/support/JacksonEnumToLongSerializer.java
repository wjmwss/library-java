package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Enum =&gt; Enum 内部 Long 类型的值
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumToLongSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> prepareToSerializerEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(EnumsUtil.getEnumValue(prepareToSerializerEnum, Long.class));
    }

}