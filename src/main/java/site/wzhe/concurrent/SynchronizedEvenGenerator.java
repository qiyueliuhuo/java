package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/10.
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;  // must be private

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
