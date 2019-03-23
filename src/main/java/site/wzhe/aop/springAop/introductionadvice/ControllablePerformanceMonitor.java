package site.wzhe.aop.springAop.introductionadvice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import site.wzhe.aop.example.PerformanceMonitor;

/**
 * Created by wangzhe on 2018/1/28.
 */
/*
    Spring提供引介增强接口，该接口没有定义任何方法，
    Spring为该接口提供了DelegatingIntroductionIntercepter实现类
    一般情况下，通过扩展该实现类定义自己的引介增强类
 */
public class ControllablePerformanceMonitor extends
        DelegatingIntroductionInterceptor implements Monitorable{

    // 为解决单实例线程安全的问题，通过ThreadLocal让每一个线程单独使用一个状态变量。
    private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<Boolean>(); // 1

    @Override
    public void setMonitorActive(boolean active) {
        monitorStatusMap.set(active);
    }

    // 拦截方法
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;

        // 对于 支持性能监视可控代理， 通过判断其状态决定是否开启性能监控功能
        if (monitorStatusMap.get() != null && monitorStatusMap.get() ) {
            PerformanceMonitor.begin(mi.getClass().getName() + "." +
                                    mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        } else {
            obj = super.invoke(mi);
        }
        return obj;
    }
}
