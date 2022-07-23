package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.StringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

/**
 * Jackson 反序列化：Enum属性值 => Enum
 *
 * @author wjm
 * @date 2020/12/15 17:02
 */
public class JacksonCapableToEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            String prepareToDeserializerValue = jsonParser.getText();
            String prepareToDeserializerFieldName = jsonParser.getCurrentName();
            Class<?> classContainPrepareToDeserializerField = jsonParser.getCurrentValue().getClass();
            Class<?> prepareToDeserializerClass = classContainPrepareToDeserializerField.getDeclaredField(prepareToDeserializerFieldName).getType();
            if (EnumsUtil.isNotEnum(prepareToDeserializerClass)) {
                throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是枚举类型, 无法反序列化，请检查！", classContainPrepareToDeserializerField.getName(), prepareToDeserializerFieldName));
            }
            return EnumsUtil.capableToEnum(Objects.getActualValue(prepareToDeserializerValue), (Class<E>) prepareToDeserializerClass);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
