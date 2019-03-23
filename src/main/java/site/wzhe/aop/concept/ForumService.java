package site.wzhe.aop.concept;

public class ForumService {
    // 事物管理
    private TransactionManager transactionManager;
    // 性能监测
    private PerformanceMonitor performanceMonitor;

    private TopicDAO topicDAO;
    private ForumDAO forumDAO;

    ForumService() {
        transactionManager = new TransactionManager();
        performanceMonitor = new PerformanceMonitor();

        topicDAO = new TopicDAO();
        forumDAO = new ForumDAO();
    }

    /*
    NOTE: 横切逻辑应用场景：性能监控　访问控制　事务管理及日志记录.
          下面的这种传统方法: 会将@1 @2处的业务逻辑淹没在重复化非业务性的代码之中
          aop技术通过横向抽取机制,将这些分散在各个业务逻辑代码中的相同代码通过横向
          切割的方式抽取到一个独立的模块中,还业务逻辑类一个清新的世界.
          概念：1. 特定点：任何程序执行的某个特定的位置.
               2. 连接点(joinpoint)：类 方法或者代码片段中具有[边界]性质的特定
                  点, 称为连接点.
               3. spring连接点: spring仅支持方法调用前,方法调用后,方法抛出异常
                  时及方法调用前后这些程序执行点进行织入增强.
               4. 切点(pointcut)：aop通过切点来定位特定的连接点(缺少方位), 一
                  个切点可以匹配多个连接点(相当于匹配查询).
               5. 增强(advice)：增强是织入目标连接点上的一段程序代码.
                  spring提供的增强接口都是带方位名的(即通过切点还不能准确确定一
                  个连接点),如BeforeAdvice AfterReturningAdvice
                  ThrowsAdvice等.
               因此,只有结合[切点]和[增强],才能确定特定的连接点并实施增强逻辑.

               6. 目标对象(target),增强逻辑的织入目标类.
               7. 织入(weaving)方式：
               　　 @1. 编译期织入,通过编译时,改变所织入类的内容,就是生成的字节码
               　　　　(class文件)中目标对象就已经生成增强逻辑,需要特殊的编译器.
                   @2. 类装载期织入,在classloader将字节码文件装载到内存中时,进
                   　　行织入,需要特殊的类装载器.
                   @3. 动态代理织入, 在运行期为目标对象添加增强并生成代理子类,
                       通过这个子类(代理类),代理原来类中的方法.
                       根据不同的代理方式分为两种:
                        (1) 代理类是和原类具有相同接口的类.
                        　　　疑问: 也就是只有接口中的方法才可以被织入增强了？？
                        (2)　代理类是原类的子类,继承原类的方法.
               8. 切面(aspect):由切点和增强(引介)组成,它既包含横切逻辑的定义,也
                  包含了连接点的定义.
               9. AOP技术的实现者:
                  (1) AspectJ:语言级的aop实现,需要专门的编译器用来生成遵守java
                      字节编码规范的.class文件.
                  (2) AspectWerkz:支持运行期或类转载期织入横切代码,需要特殊的类
                      装载器.
                  (3) JBoss AOP
                  (4) Spring AOP:使用纯java实现,不需要专门的编译器,也不需要特殊
                      的类装载器,它在运行期通过代理的方式向目标类织入增强代码,利用
                      反射机制生成代理类.
     */
    public void removeTopic(int topicId) {
        //性能监测开始
        performanceMonitor.start();
        //开始事务
        transactionManager.benginTransaction();
        topicDAO.removeTopic(topicId);  // @1
        //提交事务
        transactionManager.commit();
        //性能监测结束
        performanceMonitor.end();

    }
    public void createForum(Forum forum) {
        //性能监测开始
        performanceMonitor.start();
        //开始事务
        transactionManager.benginTransaction();
        forumDAO.create(forum);   // @2
        //提交事务
        transactionManager.commit();
        //性能监测结束
        performanceMonitor.end();

    }
}
