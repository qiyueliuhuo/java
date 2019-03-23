package site.wzhe.aop.example;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class PerformanceMonitor {

    // 通过一个ThreadLocal保存与调用线程相关的性能监视信息
    private static ThreadLocal<MethodPerformance> performanceRecod =
                                        new ThreadLocal<MethodPerformance>();

    // 启动对某一目标方法的性能监视
    public static void begin(String method) {
        System.out.println("begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        performanceRecod.set(mp);
    }

    public static void end() {
        System.out.println("end monitor");
        MethodPerformance mp = performanceRecod.get();

        // 打印出方法性能监视的结果信息
        mp.printPerformance();
    }
}
