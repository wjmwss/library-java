package org.horizon.library.java.web.openfeign.autoconfigue;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.contract.model.protocol.TransportModel;
import org.horizon.library.java.web.openfeign.interceptor.OpenFeignClientResponseInterceptor;

import java.lang.annotation.*;

/**
 * enable {@link OpenFeignClientResponseInterceptor}
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableOpenFeignClientResponseModelResolver {

    /**
     * the response models to resolve
     *
     * @return the response models to resolve
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    Class<? extends TransportModel>[] value();

}