package site.wzhe.aop.cglibProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import site.wzhe.aop.example.PerformanceMonitor;

import java.lang.reflect.Method;

/**
 * Created by wangzhe on 2018/1/28.
 */
/*
    使用jdk创建代理有一个限制，即它只能为接口创建代理实例
    CGLib采用底层的字节码技术，可以为一个类创建子类，可以为一个类创建子类，可以为一个类创建子类
    在子类中采用方法拦截的技术拦截所有父类方法的调用并顺势织入横切逻辑。
 */
public class CglibProxy implements MethodInterceptor {
    // 此段开始，这段应该也可以放在其他类中，即此类仅仅是定义方法拦截器的
    private Enhancer enhancer = new Enhancer();


    public Object getProxy(Class clazz) {

        // core
        enhancer.setSuperclass(clazz); // 1. 设置需要创建子类的类
        enhancer.setCallback(this); // 2. 设置方法拦截器对象

        // 通过字节码技术动态创建子类实例
        return enhancer.create(); // 3. 返回代理子类对象
    }
    // 此段结束

    //                             拦截父类的所有方法调用
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
        Object result = methodProxy.invokeSuper(obj, args);
        PerformanceMonitor.end();

        /*
           NOTE:
            PerformanceMonitor.begin(.end)仅仅是监视横切代码逻辑，只不过封装在类PerformanceMonitor的方法中了。
            用户可以通动态过getProxy(Class clazz)方法为一个类创建动态代理对象。
         */
        return result;
    }
}
