package cn.srd.itcp.sugar.security.sa.token.support;

import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.context.annotation.DependsOn;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token web mvc 配置
 *
 * @author wjm
 * @since 2022-07-07
 */
public class SaTokenWebMvcConfig implements WebMvcConfigurer {

    /**
     * 代表所有 URI 的路径匹配符
     */
    private static final String MATCH_ALL_ENDPOINTS_PATTER = "/**";

    /**
     * 注册拦截器
     *
     * @param interceptorRegistry 拦截器注册器
     */
    @DependsOn("sugarSaTokenAutoConfiguration")
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry interceptorRegistry) {
        SaTokenPreEachRequestAnnotationInterceptor saTokenPreEachRequestAnnotationInterceptor = SpringsUtil.getBean(SaTokenPreEachRequestAnnotationInterceptor.class);
        if (Objects.isNotNull(saTokenPreEachRequestAnnotationInterceptor)) {
            interceptorRegistry
                    // 注册每次请求前的注解式拦截器
                    .addInterceptor(saTokenPreEachRequestAnnotationInterceptor)
                    // 对所有路径生效
                    .addPathPatterns(MATCH_ALL_ENDPOINTS_PATTER);
        }
    }

}
