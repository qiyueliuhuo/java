package site.wzhe.aop.springAop.beforeadvice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
// NaiveWaiter只是简单地向顾客打招呼，闷不做声地走到顾客前，提供服务
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    // 在目标类方法调用前执行
    @Override             // o 为目标类实例
    public void before(Method method, Object[] args, Object o) throws Throwable {
        // 通过args获得被增强方法的入参
        String clientName = (String)args[0];
        System.out.println("How are you! Mr."+clientName);
    }
}
