package site.wzhe.aop.springAop.introductionadvice;

import site.wzhe.aop.concept.Forum;

import java.sql.SQLException;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class ForumService {
    public void removeForum(int forumId) {
        System.out.println("模拟删除Forum");
    }
    public void updateForum(Forum forum) {
        System.out.println("模拟更新Forum");
    }
}
