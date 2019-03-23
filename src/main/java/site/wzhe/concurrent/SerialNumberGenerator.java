package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/10.
 */
public class SerialNumberGenerator {
    public static volatile int serialNumber = 0;

    public static synchronized int nextSerialNumber() {
        return serialNumber++;   // if no synchronized Not thread-safe
    }
}
