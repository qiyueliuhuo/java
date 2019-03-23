package site.wzhe.cache.mycache;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/6/12.
 */
public class UserMain {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // 开始查询账号
        // 第一次查询，应该是数据库查询
        userService.getUserById("001001");
        // 第二次查询，应该是直接从缓存中返回
        userService.getUserById("001001");

        userService.reload();

        System.out.println("after reload...");

        // 应该是数据库查询
        userService.getUserById("001001");

        // 第二次查询，应该是直接从缓存返回
        userService.getUserById("001001");
    }
}
