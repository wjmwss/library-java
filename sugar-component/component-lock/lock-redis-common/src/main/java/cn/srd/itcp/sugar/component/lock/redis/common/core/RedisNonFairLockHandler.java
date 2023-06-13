package cn.srd.itcp.sugar.component.lock.redis.common.core;

import cn.srd.itcp.sugar.context.redis.core.RedisManager;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RLock;

/**
 * Redis 分布式单点非公平锁操作，参考 {@link RedisNonFairLock}
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedisNonFairLockHandler implements RedisLockTemplate {

    /**
     * instance
     */
    public static RedisNonFairLockHandler instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static RedisNonFairLockHandler getInstance() {
        return instance;
    }

    @Override
    public RLock getRLock(String lockName) {
        return RedisManager.getClient().getLock(lockName);
    }

}
