package site.wzhe.aop.springAop.aroundadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.aop.springAop.Waiter;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class aroundAdviceTest {
    public static void BySpringConfig(){
        // 对通过spring配置文件获得aop动态代理对象测试
        String configPath = "site/wzhe/aop/springAop/aroundadvice/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)ac.getBean("waiter");
        waiter.greetTo("John");

        // 在web应用时，通过指定的WebApplicationContext来通过配置文件生成beans
        // 且WebApplicationContext需要通过servlet技术中指定的listener或者指定的servlet来加载WebApplicationContext
    }

    public static void main(String[] args) {
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
