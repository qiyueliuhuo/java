package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/9.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("waiting for LiftOff");  // 主线程会等待其他线程
    }
}
