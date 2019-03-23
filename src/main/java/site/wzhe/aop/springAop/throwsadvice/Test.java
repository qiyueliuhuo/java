package site.wzhe.aop.springAop.throwsadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.aop.concept.Forum;
import site.wzhe.aop.springAop.Waiter;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Test {
    public static void BySpringConfig() {
        // 对通过spring配置文件获得aop动态代理对象测试
        String configPath = "site/wzhe/aop/springAop/throwsadvice/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ac.getBean("forumService");
        try {
            forumService.updateForum(new Forum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        forumService.removeForum(10);

    }
    public static void main(String[] args) {
        BySpringConfig();
    }
    /*
        输出结果：
        ------------
        method:updateForum
        抛出异常:数据库更新操作异常
        成功回滚事务
        ------------
        method:removeForum
        抛出异常:运行异常
        成功回滚事务

        NOTE: 可见，ForumService的两个方法所抛出的异常都被TransactionManager这个
        异常抛出增强捕获并成功处理。这样ForumService就从事务管理繁复的代码中解放出来，历史揭开了崭新的一页。
     */
}
