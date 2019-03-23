package site.wzhe.aop.example;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class ForumServiceTest {
    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }
}
