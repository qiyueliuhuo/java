package site.wzhe.aop.springAop.throwsadvice;

import site.wzhe.aop.concept.Forum;

import java.sql.SQLException;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class ForumService {
    public void removeForum(int forumId) {
        throw new RuntimeException("运行异常");
    }
    public void updateForum(Forum forum) throws Exception {
        throw new SQLException("数据库更新操作异常");
    }
}
