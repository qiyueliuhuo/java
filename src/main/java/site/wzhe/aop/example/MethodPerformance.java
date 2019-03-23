package site.wzhe.aop.example;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformance(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        // 记录目标类方法开始执行点的系统时间
        this.begin = System.currentTimeMillis();
    }
    public void printPerformance() {
        // 获取目标类方法执行完成后的系统时间，进而计算出目标类方法的执行时间
        end = System.currentTimeMillis();
        long elapse = end - begin;
        // 报告目标类方法的执行时间
        System.out.println(serviceMethod + "花费" + elapse + "毫秒");
    }
}
