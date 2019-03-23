package site.wzhe.pigeon.invoker;

import com.dianping.pigeon.remoting.invoker.concurrent.FutureFactory;
import com.dianping.pigeon.remoting.invoker.config.annotation.Reference;
import site.wzhe.pigeon.api.EchoService;

import java.util.concurrent.Future;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
public class AnnotationInvoker {


//    @Reference(timeout = 1000)
//    private EchoService echoService;

    @Reference(timeout = 1000, callType = "future")
    private EchoService echoService;

    public String testEcho(String input) throws Exception {
    // return echoService.echo(input);

        // 异步调用
        echoService.echo(input);
        Future future = FutureFactory.getFuture();
        return (String)future.get();
    }
}
