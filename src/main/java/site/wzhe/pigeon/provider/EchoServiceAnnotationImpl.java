package site.wzhe.pigeon.provider;

import com.dianping.pigeon.remoting.provider.config.annotation.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import site.wzhe.pigeon.api.EchoService;

import java.io.IOException;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
@Service
public class EchoServiceAnnotationImpl implements EchoService {
    @Override
    public String echo(String input) {
        return "annotation service echo:" + input;
    }

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"site/wzhe/pigeon/provider/applicationContext.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}
