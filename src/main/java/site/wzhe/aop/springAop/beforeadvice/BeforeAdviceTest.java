package site.wzhe.aop.springAop.beforeadvice;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.aop.springAop.NaiveWaiter;
import site.wzhe.aop.springAop.Waiter;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class BeforeAdviceTest {

    @Test
    public static void before() {
        // 增强的目标类
        Waiter target = new NaiveWaiter();
        // 增强
        BeforeAdvice advice = new GreetingBeforeAdvice();

        // 使用ProxyFactory比直接使用CGLib或JDK动态代理技术代理省了很多事，
        // 相当于ProxyFactory将CGLib和JDK代理封装，直接使用ProxyFactory就可以使用CGLib和JDK代理
        // 疑问：这个代理工厂只生产一种对象的代理？？？？
        // 1. spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        // 2. 设置代理目标
        pf.setTarget(target); // 如果是使用CGLib方式，需要有接口吗？ 不需要
        // 3. 为代理目标添加增强
        pf.addAdvice(advice);

        // 4. 生成代理实例
        Waiter proxy = (Waiter)pf.getProxy();
        // 在打招呼前加How are you! name
        proxy.greetTo("John");
        // 在服务前也加How are you! name
        proxy.serveTo("Tom");
    }
    public static void BySpringConfig(){
        // 对通过spring配置文件获得aop动态代理对象测试
        String configPath = "site/wzhe/aop/springAop/beforeadvice/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)ac.getBean("waiter");
        waiter.greetTo("John");

        // 在web应用时，通过指定的WebApplicationContext来通过配置文件生成beans
        // 且WebApplicationContext需要通过servlet技术中指定的listener或者指定的servlet来加载WebApplicationContext
    }

    public static void main(String[] args) {
        //before();
        BySpringConfig();
    }
    /*
        NOTE:
        此方法使用的是CGLib动态代理技术，当我们指定针对接口进行代理时，将使用JDK动态代理技术
        ProxyFactory pf = new ProxyFactory();
        pf.setInterfaces(target.getClass().getInterfaces());
        pf.setTarget(target);
        pf.addAdvice(advice);
        这时采用JDK动态代理技术
        如果采用pf.setOptimize(true); 启用优化，又将使用CGLib动态代理技术。

        pf.setAdvice(advice);
        可以添加多个增强，形成增强链，按照添加顺序执行。

        输出结果：
        How are you! Mr.John
        greet to John...
        How are you! Mr.Tom
        serving Tom...
     */
}
