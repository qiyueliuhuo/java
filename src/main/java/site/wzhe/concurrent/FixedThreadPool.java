package site.wzhe.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/9.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        // Constructor argument is number of threads
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
