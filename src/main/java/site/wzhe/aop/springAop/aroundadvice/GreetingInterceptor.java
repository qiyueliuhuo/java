package site.wzhe.aop.springAop.aroundadvice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class GreetingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object[] args = methodInvocation.getArguments(); // 获得目标方法入参
        String clientName = (String)args[0];

        // 在目标方法前执行
        System.out.println("How are you! Mr." + clientName);
        Object obj = methodInvocation.proceed(); // 通过反射机制调用目标方法
        // 在目标方法后执行
        System.out.println("Please enjoy yourself!");
        return obj;
    }
    /*
        NOTE:
        Spring直接使用AOP联盟所定义的MethodInterceptor作为环绕增强的接口。
     */
}
