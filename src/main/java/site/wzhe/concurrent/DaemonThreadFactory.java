package site.wzhe.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/10.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
