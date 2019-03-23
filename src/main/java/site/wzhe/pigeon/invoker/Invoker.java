package site.wzhe.pigeon.invoker;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.pigeon.api.EchoService;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
public class Invoker {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"site/wzhe/pigeon/invoker/applicationContext.xml"});
                context.start();
        EchoService echoService = (EchoService)context.getBean("echoService"); // 获取远程服务代理
                String hello = echoService.echo("world");
        System.out.println(hello);
    }
}
