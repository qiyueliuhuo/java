package site.wzhe.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/20.
 */
@Slf4j
public class HelloShiro {

    public static void main(String[] args) {
        // 初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 获取当前用户（主体）
        Subject subject = SecurityUtils.getSubject();
        // 登录
        UsernamePasswordToken token = new UsernamePasswordToken("shiro", "201314");
        try {
            // 登录成功后，Shiro创建了一个Session
            subject.login(token);
        } catch (AuthenticationException ae) {
            log.info("登录失败");
            return;
        }
        // getPrincipal()  获取当前用户的UserName
        log.info("登录成功！Hello " + subject.getPrincipal());
        subject.logout();
    }

}
