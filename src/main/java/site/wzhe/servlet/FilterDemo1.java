package site.wzhe.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangzhe on 2018/1/7.
 */
public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // 初始化
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1 go");
        //过滤处理
        chain.doFilter(request, response);  //神奇
        System.out.println("filter1 down");
    }
    @Override
    public void destroy() {
        // 释放资源
    }
}
