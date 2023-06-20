package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheEvict;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for {@link CacheEvict}
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CacheEvictAspect implements CacheAspect {

    /**
     * the pointcut for {@link CacheEvict}
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheEvict)")
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
        CacheEvict annotation = getAnnotationMarkedOnMethod(joinPoint, CacheEvict.class);
        CacheAspectContext context = buildCacheEvictContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowNullValue(), annotation.needEvictBeforeProceed(), annotation.needEvictAllInNamespaces());
        return doEvict(joinPoint, context, this::doProceed);
    }

}
