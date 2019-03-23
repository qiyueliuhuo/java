package site.wzhe.aop.springAop.introductionadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.aop.concept.Forum;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Test {
    public static void BySpringConfig() {
        // 对通过spring配置文件获得aop动态代理对象测试
        String configPath = "site/wzhe/aop/springAop/introductionadvice/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ac.getBean("forumService");

        forumService.updateForum(new Forum());
        forumService.removeForum(10);
        System.out.println("--------------------------");

        // 开启性能监控
        Monitorable monitorable = (Monitorable)forumService;
        monitorable.setMonitorActive(true);

        forumService.updateForum(new Forum());
        forumService.removeForum(10);

    }

    public static void main(String[] args) {
        BySpringConfig();
    }
    /*
        运行结果：
        模拟更新Forum
        模拟删除Forum
        --------------------------
        begin monitor...
        模拟更新Forum
        end monitor
        org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.updateForum花费1毫秒
        begin monitor...
        模拟删除Forum
        end monitor
        org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.removeForum花费0毫秒
       */
}
