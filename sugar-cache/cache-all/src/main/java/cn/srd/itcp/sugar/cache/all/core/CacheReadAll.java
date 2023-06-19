package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheComponentType;
import org.springframework.cache.support.NullValue;

import java.lang.annotation.*;

/**
 * annotation to read all cache
 * TODO wjm unimplemented：condition、unless
 *
 * @author wjm
 * @since 2023-06-18 02:20:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheReadAll {

    /**
     * see {@link CacheConfig#namespaces()}
     *
     * @return the cache namespace
     */
    String[] namespaces() default {};

    /**
     * see {@link CacheConfig#cacheComponentTypes()}
     *
     * @return the cache type
     */
    CacheComponentType[] cacheComponentTypes() default {};

    /**
     * <pre>
     * see {@link CacheRead#key()}.
     * when cannot find value in cache, it will execute pointcut and use this field to generate a key and set to cache.
     * </pre>
     *
     * @return the cache key
     */
    String key() default "";

    /**
     * see {@link CacheConfig#allowNullValue()}
     *
     * @return allow or not to set a {@link NullValue} in cache
     */
    boolean allowNullValue() default false;

}
