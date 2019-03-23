package site.wzhe.aop.springAop.afteradvice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
// 在前置增强中测试
public class GeetingAfterAdvice implements AfterReturningAdvice {
    @Override      // obj为目标类实例
    public void afterReturning(Object returnObj, Method method, Object[] args, Object obj) throws Throwable {
        System.out.println("Please enjoy yourself!");
    }
}
