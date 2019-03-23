package site.wzhe.threadpool.spring;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/24.
 */
@Service
public class MultiThreadProcessService
{

    public static final Logger logger = Logger.getLogger(MultiThreadProcessService.class);

    /**
     * 默认处理流程耗时1000ms
     */
    public void processSomething() {
        logger.debug("MultiThreadProcessService-processSomething" + Thread.currentThread() + "......start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.debug("MultiThreadProcessService-processSomething" + Thread.currentThread() + "......end");
    }
}
