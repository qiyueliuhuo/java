package site.wzhe.cache.caches;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import site.wzhe.cache.domain.User;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/6/12.
 */
@Service(value = "userServiceBean")
public class UserService {

    // 使用一个名为users的缓存
    @Cacheable(cacheNames = "users")
//    @Cacheable(value = "common", key = "#userId")
    public User getUserById(String userId) {

        // 方法内部实现不考虑缓存逻辑，直接实现业务
        System.out.println("real query user." + userId);
        return getFromDB(userId);
    }
    @CacheEvict(cacheNames = "users")
    public void deleteUser(String userId) {
        System.out.println("delete user by id: " + userId);
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }
}
