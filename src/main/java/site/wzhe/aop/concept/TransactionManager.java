package site.wzhe.aop.concept;

public class TransactionManager {
    public void benginTransaction() {
        System.out.println("开始事务");
    }

    public void commit() {
        System.out.println("提交事务");
    }
}
