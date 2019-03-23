package site.wzhe.cache.mycache;

import site.wzhe.cache.domain.User;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/6/12.
 */
public class UserService {
    private CacheManager<User> cacheManager;

    public UserService() {
        cacheManager = new CacheManager<User>();
    }

    public User getUserById(String userId) {

        // 首先查询缓存
        User result = cacheManager.getValue(userId);
        if (result != null) {
            System.out.println("get from cache..." + userId);

            // 如果在缓存中国，则直接返回缓存的结果
            return result;
        }

        // 否则到数据库中查询
        result = getFromDB(userId);
        if (result != null) {
            // 将数据库查询到的结果更新到缓存中
            cacheManager.addOrUpdateCache(userId, result);
        }
        return result;
    }

    public void reload() {
        cacheManager.evictCache();
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }
}
