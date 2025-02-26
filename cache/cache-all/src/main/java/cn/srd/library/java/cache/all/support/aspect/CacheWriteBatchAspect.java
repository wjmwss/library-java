package cn.srd.library.java.cache.all.support.aspect;

import cn.srd.library.java.cache.all.core.CacheWrite;
import cn.srd.library.java.cache.all.core.CacheWriteBatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * Aspect for {@link CacheWrite}
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CacheWriteBatchAspect implements CacheAspect {

    /**
     * the pointcut for {@link CacheWrite}
     */
    @Pointcut("@annotation(cn.srd.library.java.cache.all.core.CacheWriteBatch)")
    public void pointcut() {
    }

    /**
     * the around logic for pointcut
     *
     * @param joinPoint pointcut
     * @param <T>       the cache value type
     * @return logic return
     */
    @Around("pointcut()")
    public <T> List<T> aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheWriteBatch annotation = getAnnotationMarkedOnMethod(joinPoint, CacheWriteBatch.class);
        CacheAspectContext context = buildCacheWriteContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.cacheMode(), annotation.key(), annotation.allowEmptyValue());
        return doWriteBatch(joinPoint, context, this::doProceed);
    }

}
