package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:  java编程思想 第二十一章
 * @date: 2018/7/9.
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;  // Default
    private static int taskCount = 0;
    private final int id = taskCount++; // divide task

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "),";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}
