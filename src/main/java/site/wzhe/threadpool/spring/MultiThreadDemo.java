package site.wzhe.threadpool.spring;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/24.
 */
public class MultiThreadDemo implements Runnable {

    private MultiThreadProcessService multiThreadProcessService;

    public MultiThreadDemo() {
    }

    public MultiThreadDemo(MultiThreadProcessService multiThreadProcessService) {
        this.multiThreadProcessService = multiThreadProcessService;
    }

    @Override
    public void run() {
        multiThreadProcessService.processSomething();
    }

}
