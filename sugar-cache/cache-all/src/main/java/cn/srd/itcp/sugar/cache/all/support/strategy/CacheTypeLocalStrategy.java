package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * the local cache type strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheTypeLocalStrategy implements CacheTypeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheTypeLocalStrategy INSTANCE = new CacheTypeLocalStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheTypeLocalStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        Object value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
        if (Objects.isNotNull(value)) {
            for (int writeIndex = findCacheTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(writeIndex));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), value);
            }
        }
        return value;
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        Map<String, V> values = cacheTemplate.getMapByNamespace(namespace);
        if (Objects.isNotEmpty(values)) {
            for (int writeIndex = findCacheTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(writeIndex));
                for (Map.Entry<String, V> value : values.entrySet()) {
                    cacheTemplate.set(value.getKey(), value.getValue());
                }
            }
        }
        return values;
    }

}


