package cn.srd.library.java.concurrent.redis;

import cn.srd.library.java.concurrent.redis.autoconfigue.RedisLockAutoConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable redis lock system
 *
 * @author wjm
 * @see RedisLockSwitcher
 * @see RedisLockAutoConfigurer
 * @since 2023-06-13 17:26:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisLockSwitcher.class)
public @interface EnableRedisLock {

}
