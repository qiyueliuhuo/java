package site.wzhe.aop.springAop.pointcut.staticpointcut;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override             // o 为目标类实例
    public void before(Method method, Object[] args, Object o) throws Throwable {
        //输出切点
        System.out.println("切点是：" + o.getClass().getName() + "." + method.getName());

        // 通过args获得被增强方法的入参
        String clientName = (String)args[0];
        System.out.println("How are you! Mr."+clientName);
    }
}
