package xmu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String firstName = req.getParameter("firstname");
        String bornPlace = req.getParameter("bornplace");

        PrintWriter out = resp.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");

        out.println("<head>");
        out.println("<title>处理get提交的数据</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>欢迎使用Servlet</h1><br/>");
        out.println("提交的姓名是："+firstName);
        out.println("<br/>提交的籍贯是："+bornPlace);
        out.println("</body>");

        out.println("</html>");
        out.close();
    }
}
