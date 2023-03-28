package cn.srd.itcp.sugar.framework.spring.tool.common.core.enums.autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

/**
 * 指定在哪些包下扫描标记了 {@link EnumAutowired} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
 *
 * @author wjm
 * @since 2022-07-20 11:37:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnumAutowiredScan {

    /**
     * <pre>
     * 指定在哪些包下扫描标记了 {@link EnumAutowired} 的类；
     * 若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描；
     * 若指定，则在 {@link SpringBootApplication} 所在的包路径 + 指定的路径下扫描；
     * </pre>
     *
     * @return 待扫描的包路径集合
     */
    String[] value() default {};

}
