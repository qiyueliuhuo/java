package site.wzhe.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangzhe on 2018/1/7.
 */
public class ServletDemo1 implements Servlet {
    @Override
    public void destroy() {

    }
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    @Override
    public String getServletInfo() {
        return null;
    }
    @Override
    public void init(ServletConfig arg0) throws ServletException {

    }
    @Override
    public void service(ServletRequest request, ServletResponse response)
                throws ServletException, IOException {
        PrintWriter pwt = response.getWriter();
        pwt.println("I am ServletDemo1");
        pwt.close();
    }
}
