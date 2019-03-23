package site.wzhe.aop.jdkProxy;


import java.lang.reflect.Proxy;

/**
 * Created by wangzhe on 2018/1/28.
 */
/*
    自java 1.3以后，java提供了动态代理技术，允许开发者在运行期创建接口，接口，接口的代理实例。
    jdk.lang.reflect.Proxy利用InvocationHandler动态创建一个符合某一接口的实例，生成目标类的代理对象。
    Proxy Proxy Proxy
 */
public class ForumServiceTest {
    public static void main(String[] args) {
        // 希望被代理的目标业务类
        ForumService  target = new ForumServiceImpl();

        // 将目标业务类和横切代码编织到一起
        PerformanceHandler handler = new PerformanceHandler(target);

        //根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
        ForumService proxy = (ForumService) Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    handler);
        /*
        自己思考: Proxy.newProxyInstance() 需要一个类加载器，所有的接口，和一个实现了InvoctionHandler
                生成一个接口的代理类，类中调用接口中的方法，都将调用InvocationHandler中的invoker方法。
                所以可以在InvocationHandler的invoker中将横切代码和被执行对象加入其中。
                疑问：动态代理如何实现的？
                猜测：代理类实现所有接口，并在覆盖接口的方法中去调用InvocationHandler中的invoker。
         */
        System.out.println(proxy);
        proxy.removeForum(10);
        proxy.removeTopic(1012);
        /*
          输出结果：
            Connected to the target VM, address: '127.0.0.1:65486', transport: 'socket'
            begin monitor...
            模拟删除Forum记录:10
            end monitor
            site.wzhe.aop.jdkProxy.ForumServiceImpl.removeForum花费44毫秒
            begin monitor...
            模拟删除Topic记录:1012
            end monitor
            site.wzhe.aop.jdkProxy.ForumServiceImpl.removeTopic花费23毫秒
            Disconnected from the target VM, address: '127.0.0.1:65486', transport: 'socket'
         */
    }
}
