package site.wzhe.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangzhe on 2018/1/7.
 */
public class ServletDemo2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>Servlet实例</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("  Servlet...");
        out.print(this.getClass());
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        System.out.println("ServletDemo2 finish");
    }
}

