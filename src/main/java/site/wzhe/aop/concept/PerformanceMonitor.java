package site.wzhe.aop.concept;

public class PerformanceMonitor {
    public void start() {
        System.out.println("性能监测开始,记录运行时间");
    }
    public void end() {
        System.out.println("性能监测结束,运行时长");
    }
}
