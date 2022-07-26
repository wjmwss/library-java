package cn.srd.itcp.sugar.security.sa.token.core;

import cn.srd.itcp.sugar.security.sa.token.support.SaTokenWebExceptionHandler;

import java.lang.annotation.*;

/**
 * 启用 {@link SaTokenWebExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableSaWebExceptionHandler {

}
