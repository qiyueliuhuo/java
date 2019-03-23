package site.wzhe.aop.springAop.pointcut.staticpointcut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.aop.springAop.pointcut.Seller;
import site.wzhe.aop.springAop.pointcut.Waiter;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Test {
    public static void BySpringConfig() {
        // 对通过spring配置文件获得aop动态代理对象测试
        String configPath = "site/wzhe/aop/springAop/pointcut/staticpointcut/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter = (Waiter)ac.getBean("waiter");
        Seller seller = (Seller)ac.getBean("seller");

        waiter.greetTo("you");
        System.out.println("----------------");
        waiter.serveTo("wu");
        seller.greetTo("wu");

        System.out.println();
        System.out.println("---------通过RegexpMethodPointcutAdvisor匹配得到切点-------");
        Waiter waiter1 = (Waiter)ac.getBean("waiter1");
        waiter1.greetTo("you");
        waiter1.serveTo("wu");
    }

    public static void main(String[] args) {
        BySpringConfig();
    }
    /*
        NOTE:
            可见，切面只在Waiter.greetTo()方法调用前的连接点上，Waiter.serveTo()和
            Seller.greetTo()方法没有织入切面
        运行结果：
        切点是：site.wzhe.aop.springAop.pointcut.Waiter.greetTo
        How are you! Mr.you
        site.wzhe.aop.springAop.pointcut.Waiter greet to you...
        ----------------
        site.wzhe.aop.springAop.pointcut.Waiter serving wu...
        site.wzhe.aop.springAop.pointcut.Seller seller greet to wu

        ---------通过RegexpMethodPointcutAdvisor匹配得到切点-------
        切点是：site.wzhe.aop.springAop.pointcut.Waiter.greetTo
        How are you! Mr.you
        site.wzhe.aop.springAop.pointcut.Waiter greet to you...
        site.wzhe.aop.springAop.pointcut.Waiter serving wu...
       */
}
