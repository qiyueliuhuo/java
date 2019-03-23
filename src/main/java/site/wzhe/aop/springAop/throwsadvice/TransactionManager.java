package site.wzhe.aop.springAop.throwsadvice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
// 事务管理：是一个ThrowAdvice。在出现异常时进行事务回滚
public class TransactionManager implements ThrowsAdvice {

    // 定义增强逻辑, ThrowsAdvice是一个标签接口，但方法签名有一系列合法p238的，如：
    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception ex) throws Throwable {
        System.out.println("------------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + ex.getMessage());
        System.out.println("成功回滚事务");
    }

}
