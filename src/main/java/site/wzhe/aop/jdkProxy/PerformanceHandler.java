package site.wzhe.aop.jdkProxy;

import site.wzhe.aop.example.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */

/*
    业务类中移除性能横切代码后，必须为它找到一个安身之所，InvocatoinHandler就是横切代码的"安居乐园"
 */
                                // 实现InvocationHandler
public class PerformanceHandler implements InvocationHandler {
    private Object target;
    // target为目标业务类
    public PerformanceHandler(Object target) {
        this.target = target;
    }

    /*
        通过这种方法，发现性能监测的横切代码只出现一次，只出现一次，而不是像原来那样散落在各处。
     */
    // invoke中参数，proxy是最终生成的代理实例，一般不会用到。
    // method是被代理实例的某个具体方法
    // args是被代理实例某个方法的入参
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        // 通过java反射方法间接调用业务类的目标方法，这样InvocationHandler看出一个编织器
        // 将横切代码与业务逻辑代码编织到一起
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
