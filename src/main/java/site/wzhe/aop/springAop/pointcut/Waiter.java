package site.wzhe.aop.springAop.pointcut;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Waiter implements site.wzhe.aop.springAop.Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println(getClass().getName() + " greet to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println(getClass().getName() + " serving " + name + "...");
    }
}
