package site.wzhe.aop.springAop.pointcut.staticpointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import site.wzhe.aop.springAop.Waiter;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
// 希望通过StaticMethodMatcherPointcutAdvisor定义一个切面，给Waiter#greetTo方法前织入一个增强
    // 还需要一个增强的配合
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> clazz) { // 切点方法匹配规则
        return "greetTo".equals(method.getName());
    }
    // 唯一需要定义的是matches，这里通过覆盖getClassFilter()方法，让它匹配Waiter类及其子类
    // 默认匹配所有的类（所有目标类吧？）
    @Override
    public ClassFilter getClassFilter() { // 切点类匹配规则
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return Waiter.class.isAssignableFrom(clazz); // ??
            }
        };
    }
}
