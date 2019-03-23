package site.wzhe.aop.springAop.introductionadvice;

/**
 * Created by wangzhe on 2018/1/28.
 */
// 此接口用来标识支持性能监视可控
public interface Monitorable {
    void setMonitorActive(boolean active);
}
