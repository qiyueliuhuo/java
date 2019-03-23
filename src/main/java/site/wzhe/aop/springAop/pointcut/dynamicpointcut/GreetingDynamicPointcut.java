package site.wzhe.aop.springAop.pointcut.dynamicpointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import site.wzhe.aop.springAop.pointcut.Waiter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhe on 2018/1/29.
 */
/*
    动态切面，在运行时根据入参判断是否进行增强
    DynamicMethodMatcherPointcutAdvisor在spring 2.0中已经过期
    可以使用 DefaultPointcutAdvisor和DynamicMethodMatcherPointcut来完成相同的功能。
 */
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {
    // 模拟特殊客户名单
    private static List<String> specialClientList = new ArrayList<String>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }

    // 对类进行静态切点检查
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                System.out.println("调用getClassFilter()对" + clazz.getName() + "做静态检查");
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }

    // 对方法进行静态切点检查
    @Override
    public boolean matches(Method method, Class clazz) {
        System.out.println("调用matches(method, clazz)" + clazz.getName() + "." +
        method.getName() + "做静态检查");

        return "greetTo".equals(method.getName());
    }

    // 对方法进行动态切点检查
    @Override
    public boolean matches(Method method, Class<?> clazz, Object... args) {
        System.out.println("调用matches(method,clazz,args)" + clazz.getName() + "."
                    + method.getName() + "做动态检查");
        // 只有在name为John或Tom时才增强
        String clientName = (String)args[0];
        return specialClientList.contains(clientName);
    }
    /*
        NOTE: 在创建代理时对目标类先进行静态检查，如果不匹配，则在运行时就不会进行动态检查了；
                只去检查静态条件满足的，再去运行时检查动态匹配。可以极大的提高运行效率
     */
}
