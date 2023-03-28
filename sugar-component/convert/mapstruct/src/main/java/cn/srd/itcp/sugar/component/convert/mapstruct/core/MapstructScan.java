package cn.srd.itcp.sugar.component.convert.mapstruct.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

/**
 * 指定在哪些包下扫描标记了 {@link BindMapstruct} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
 *
 * @author wjm
 * @since 2022/6/24 14:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Deprecated
public @interface MapstructScan {

    /**
     * 指定在哪些包下扫描标记了 {@link BindMapstruct} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
     *
     * @return 待扫描的包路径集合
     */
    String[] value();

}
