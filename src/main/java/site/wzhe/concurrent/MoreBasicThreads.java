package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description: main()中并没有捕获Thread对象的引用，每个Thread都"注册"了自己，因此确实有一个
 *              对它的引用，而且在它的任务退出其run()并死亡之前，垃圾回收器无法清除它。
 * @date: 2018/7/9.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
