package site.wzhe.aop.springAop.autoproxycreator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Test {
    public static void BySpringConfig() {
        String configPath = "site/wzhe/aop/springAop/autoproxycreator/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter = (Waiter)ac.getBean("waiter");
        Seller seller = (Seller)ac.getBean("seller");

        seller.greetTo("Tom");
        waiter.greetTo("John");

        waiter.serveTo("wu");
    }
    // 报错。。。。jdk.lang.ClassCastException: com.sun.proxy.$Proxy3 cannot be cast to site.wzhe.aop.springAop.autoproxycreator.Waite ？？？？？？？？？？？？？？？
    public static void main(String[] args) {
        BySpringConfig();
    }
    /*
        1.
        NOTE:
        通过BeanNameAutoProxyCreator为waiter和seller自动创建代理对象
        所有的自动创建器类都实现了BeanPostProcessor,在容器实例化Bean时，
        BeanPostProcessor将对它进行加工处理，让容器自动生成代理，把开发人员
        从繁琐的配置工作中解放出来。
        输出结果：
        How are you! Mr.Tom
        site.wzhe.aop.springAop.autoproxycreator.Seller seller greet to Tom
        How are you! Mr.John
        site.wzhe.aop.springAop.autoproxycreator.Waiter greet to John...
        How are you! Mr.wu
        site.wzhe.aop.springAop.autoproxycreator.Waiter serving wu...

        2.
        NOTE:
        输出结果：
       */
}
