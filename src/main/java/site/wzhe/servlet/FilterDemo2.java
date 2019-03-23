package site.wzhe.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangzhe on 2018/1/7.
 */
public class FilterDemo2 implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
        // 初始化
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("filter2 go");
        //过滤处理
        chain.doFilter(request, response);
        System.out.println("filter2 down");
    }
    public void destroy() {
        // 释放资源
    }
}