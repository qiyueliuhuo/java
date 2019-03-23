package site.wzhe.aop.springAop.pointcut.dynamicpointcut;

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
        String configPath = "site/wzhe/aop/springAop/pointcut/dynamicpointcut/beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter = (Waiter)ac.getBean("waiter");

        waiter.greetTo("ahah");

        System.out.println("----------------");

        waiter.greetTo("John");

    }

    public static void main(String[] args) {
        BySpringConfig();
    }
    /*
        NOTE:
        动态切面 动态切面 动态切面
        这种技术，可以在运行期间通过对方法入参进行条件匹配来 决定是否对类中的方法进行增强，
        通过入参，也可以做成开关（引介增强）中
        输出结果：
        调用getClassFilter()对site.wzhe.aop.springAop.pointcut.Waiter做静态检查
        调用matches(method, clazz)site.wzhe.aop.springAop.pointcut.Waiter.serveTo做静态检查
        调用getClassFilter()对site.wzhe.aop.springAop.pointcut.Waiter做静态检查
        调用matches(method, clazz)site.wzhe.aop.springAop.pointcut.Waiter.greetTo做静态检查
        调用getClassFilter()对site.wzhe.aop.springAop.pointcut.Waiter做静态检查
        调用matches(method, clazz)site.wzhe.aop.springAop.pointcut.Waiter.toString做静态检查
        调用getClassFilter()对site.wzhe.aop.springAop.pointcut.Waiter做静态检查
        调用matches(method, clazz)site.wzhe.aop.springAop.pointcut.Waiter.clone做静态检查
        调用getClassFilter()对site.wzhe.aop.springAop.pointcut.Waiter做静态检查
        调用matches(method, clazz)site.wzhe.aop.springAop.pointcut.Waiter.greetTo做静态检查
        调用matches(method,clazz,args)site.wzhe.aop.springAop.pointcut.Waiter.greetTo做动态检查
        site.wzhe.aop.springAop.pointcut.Waiter greet to ahah...
        ----------------
        调用matches(method,clazz,args)site.wzhe.aop.springAop.pointcut.Waiter.greetTo做动态检查
        How are you! Mr.John
        site.wzhe.aop.springAop.pointcut.Waiter greet to John...
       */
}
