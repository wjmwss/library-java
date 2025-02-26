package org.horizon.library.java.tool.convert.api;

import org.horizon.library.java.tool.convert.jackson.JacksonConverts;
import org.horizon.library.java.tool.convert.mapstruct.plus.MapstructConverts;
import org.horizon.library.java.tool.convert.protobuf.ProtobufConverts;
import org.horizon.library.java.tool.convert.spring.SpringConverts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all in one converter
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts extends org.horizon.library.java.tool.lang.convert.Converts {

    /**
     * apply spring converter
     *
     * @return spring converter
     */
    public static SpringConverts onSpring() {
        return SpringConverts.getInstance();
    }

    /**
     * apply jackson converter
     *
     * @return jackson converter
     */
    public static JacksonConverts onJackson() {
        return JacksonConverts.getInstance();
    }

    /**
     * apply mapstruct converter
     *
     * @return mapstruct converter
     */
    public static MapstructConverts onMapstruct() {
        return MapstructConverts.getInstance();
    }

    /**
     * apply protobuf converter
     *
     * @return protobuf converter
     */
    public static ProtobufConverts onProtobuf() {
        return ProtobufConverts.getInstance();
    }

}