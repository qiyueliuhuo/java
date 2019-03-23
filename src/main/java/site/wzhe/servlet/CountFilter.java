package site.wzhe.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wangzhe on 2018/1/7.
 */
public class CountFilter implements Filter {
    // 来访数量
    private int count;
    @Override
    public void init(FilterConfig filterConfig) {
        String param = filterConfig.getInitParameter("count");
        count = Integer.valueOf(param);
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        count++;
        // 将ServletRequest 转换为HttpServletRequest
        HttpServletRequest req = (HttpServletRequest)request;
        ServletContext context = req.getSession().getServletContext();
        //为了能访问计数器中的值，实例中将其放置于Servlet上下文中
        // 自动装箱？
        context.setAttribute("count", count);

        // 向下传递过滤器
        chain.doFilter(request,response);
    }
    @Override
    public void destroy() {

    }
}
