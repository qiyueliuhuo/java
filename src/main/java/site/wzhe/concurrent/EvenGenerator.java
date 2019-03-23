package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/10.
 */
public class EvenGenerator extends IntGenerator{

    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;  // Danger point here!
        // Thread.yield();   // 尽早发生失败
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
