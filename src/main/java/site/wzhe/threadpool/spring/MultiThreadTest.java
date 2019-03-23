package site.wzhe.threadpool.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:site/wzhe/cache/caches/applicationContext.xml")
public class MultiThreadTest {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private MultiThreadProcessService multiThreadProcessService;

    @Test
    public void test() {

        int n = 20;
        for (int i = 0; i < n; i++) {
            taskExecutor.execute(new MultiThreadDemo(multiThreadProcessService));
            System.out.println("int i is " + i + ", now threadpool active threads totalNum is " + taskExecutor.getActiveCount());
        }

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
