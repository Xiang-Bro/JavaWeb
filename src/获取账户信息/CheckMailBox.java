package 获取账户信息;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckMailBox
 */
public class CheckMailBox extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String mailBox = request.getParameter("mailBox");
		String code = request.getParameter("code");
		System.out.println("进入CheckMailBox了");
		String isCode = (String) request.getSession().getAttribute(mailBox);
		System.out.println(mailBox);
		System.out.println(isCode+code);
		System.out.println(isCode.equals(code));
		response.getWriter().print(isCode.equals(code));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
