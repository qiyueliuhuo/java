package site.wzhe.pigeon.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.pigeon.api.EchoService;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
public class EchoServiceImpl implements EchoService {
    public String echo(String name) {
        return "Hello " + name;
    }
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"site/wzhe/pigeon/provider/applicationContext.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}
