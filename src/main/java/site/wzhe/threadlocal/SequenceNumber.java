package site.wzhe.threadlocal;

/**
 * Created by wangzhe on 2018/1/31.
 */
public class SequenceNumber {

    // 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        public Integer initialValue() {
            return 0;
        }
    };
    // 获取下一个值
    public int getNextNum() {
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();

        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        // 三个线程共享sn，各自产生序列号

        t1.start();
        t2.start();
        t3.start();

    }

    private static class TestClient extends Thread {
        private SequenceNumber sn;
        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() +
                                    "] sn[" + sn.getNextNum() + "]");
            }
        }
    }

    /*
        NOTE:
        【ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。】
        jdk 5.0之后，提供了ThreadLocal<T>泛型，不需要强制类型转换。
        多线程共享同一个对象，对象中存在被函数使用的"全局"对象变量，会存在线程安全问题。
        输出结果：
        thread[Thread-0] sn[1]
        thread[Thread-2] sn[1]
        thread[Thread-1] sn[1]
        thread[Thread-2] sn[2]
        thread[Thread-0] sn[2]
        thread[Thread-2] sn[3]
        thread[Thread-1] sn[2]
        thread[Thread-0] sn[3]
        thread[Thread-1] sn[3]
     */
}
