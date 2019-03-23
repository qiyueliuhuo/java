package site.wzhe.cache.mycache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wangzhe.
 * @description: 缓存管理器。
 * @date: 2018/6/12.
 */
public class CacheManager<T> {

    private Map<String, T> cache = new ConcurrentHashMap<String, T>();

    public T getValue(Object key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    // 根据key来删除缓存中的一条记录
    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    public void evictCache() {
        cache.clear();
    }
}
