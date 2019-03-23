package site.wzhe.aop.jdkProxy;



/**
 * Created by wangzhe on 2018/1/28.
 */
public class ForumServiceImpl implements ForumService {
    @Override
    public void removeTopic(int topicId) {

        /*
            将监视位置的代码移除(抽取到切面中去)
         */
        // 开始对该方法进行性能监视
        // PerformanceMonitor.begin("site.wzhe.aop.example.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录:" + topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 结束对该方法的性能监视
        // PerformanceMonitor.end();
    }

    @Override
    public void removeForum(int forumId) {

        // 开始对该方法进行性能监视
        //PerformanceMonitor.begin("site.wzhe.aop.example.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录:" + forumId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 结束对该方法的性能监视
        // PerformanceMonitor.end();
    }
}
