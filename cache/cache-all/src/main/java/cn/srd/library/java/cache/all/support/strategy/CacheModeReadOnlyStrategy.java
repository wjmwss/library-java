package cn.srd.library.java.cache.all.support.strategy;

/**
 * the read only cache mode strategy
 *
 * @author wjm
 * @since 2023-06-19 21:26:47
 */
public class CacheModeReadOnlyStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeReadOnlyStrategy INSTANCE = new CacheModeReadOnlyStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeReadOnlyStrategy getInstance() {
        return INSTANCE;
    }

}


