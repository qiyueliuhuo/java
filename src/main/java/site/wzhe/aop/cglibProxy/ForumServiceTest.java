package site.wzhe.aop.cglibProxy;


/**
 * Created by wangzhe on 2018/1/28.
 */
public class ForumServiceTest {
    public static void proxy() {
        CglibProxy proxy = new CglibProxy();
        // 通过动态生成子类的方式创建代理类
        ForumServiceImpl forumService =
                (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }

    public static void main(String[] args) {
        proxy();
    }
    /*
        输出结果：
        Connected to the target VM, address: '127.0.0.1:65499', transport: 'socket'
        begin monitor...
        模拟删除Forum记录:10
        end monitor
        site.wzhe.aop.cglibProxy.ForumServiceImpl$$EnhancerByCGLIB$$f4ccc0e8.removeForum花费63毫秒
        begin monitor...
        模拟删除Topic记录:1023
        end monitor
        Disconnected from the target VM, address: '127.0.0.1:65499', transport: 'socket'
        site.wzhe.aop.cglibProxy.ForumServiceImpl$$EnhancerByCGLIB$$f4ccc0e8.removeTopic花费20毫秒

        NOTE:
        ForumServiceImpl$$EnhancerByCGLIB$$f4ccc0e8这个就是CGLib采用动态创建子类方式生成的代理对象
        而且不能对目标类中的final或private方法进行代理。
        疑问：实现原理还是不清楚。
     */
}
