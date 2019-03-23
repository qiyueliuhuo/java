package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/10.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    // Allow this to canceled
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
