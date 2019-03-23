package site.wzhe.aop.springAop;

/**
 * Created by wangzhe on 2018/1/28.
 */
// 训练不足的服务生服务情况
public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving " + name + "...");
    }
}
