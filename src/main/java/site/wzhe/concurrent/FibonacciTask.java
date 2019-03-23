package site.wzhe.concurrent;

/**
 * @author: wangzhe.
 * @description: 控制台上打印字符出现混乱。
 * @date: 2018/7/9.
 */
public class FibonacciTask implements Runnable {

    final private long num;
    public FibonacciTask(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();

        if (num < 1) {
            sb.append("init parameter error!");
        } else if (num == 1) {
            sb.append("1");
        } else if (num == 2) {
           sb.append("1, 1, ");
        } else {
            long f1 = 1, f2 = 1;
            sb.append("1, 1, ");
            for (int i = 2; i < num; i++) {
                long f = f1 + f2;
                sb.append(f + ", ");
                f1 = f2;
                f2 = f;
            }
        }
        System.out.println(sb.toString());  // 此操作会不会被中断？
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new FibonacciTask(10 + i)).start();
        }
    }

}
