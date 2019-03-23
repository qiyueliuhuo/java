package site.wzhe.cache.caches;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/6/13.
 */
public class UserMain {
    public static void main(String[] args) {
        // 加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/site/wzhe/cache/caches/applicationContext.xml");
        UserService userService = (UserService)context.getBean("accountServiceBean");

        // 第一次查询，应该查询数据库
        System.out.println("first query...");
        System.out.println(userService.getUserById("1111111"));

        // 删除缓存中的相关内容
        // userService.deleteUser("1111111");

        // 第二次查询，应该不查询数据库，直接返回缓存中的值
        System.out.println("second query...");
        System.out.println(userService.getUserById("1111111"));
    }
}
