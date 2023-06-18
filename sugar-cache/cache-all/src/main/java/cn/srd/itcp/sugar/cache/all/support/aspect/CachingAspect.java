package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheEvict;
import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
import cn.srd.itcp.sugar.cache.all.core.Caching;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * Aspect for {@link Caching}
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CachingAspect implements CacheAspect {

    /**
     * the pointcut for {@link Caching}
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.Caching)")
    public void pointcut() {
    }

    /**
     * the around logic for pointcut
     *
     * @param joinPoint pointcut
     * @return logic return
     */
    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Caching cachingAnnotation = getAnnotationMarkedOnMethod(joinPoint, Caching.class);
        // handle read
        List<CacheAspectContext> readContexts = CollectionsUtil.toList(cachingAnnotation.read(), annotation -> buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.allowNullValue(), null, null));
        Object value = doRead(joinPoint, readContexts);
        // handle write
        for (CacheWrite annotation : cachingAnnotation.write()) {
            CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.allowNullValue(), null, null);
            doWrite(joinPoint, context, useless -> value);
        }
        // handle evict
        for (CacheEvict annotation : cachingAnnotation.evict()) {
            CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.allowNullValue(), annotation.needEvictBeforeProceed(), annotation.needEvictAllInNamespaces());
            doEvict(joinPoint, context, useless -> value);
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

}
