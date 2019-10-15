package 获取账户信息;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FindNewsUtil;

/**
 * Servlet implementation class News_show
 */
public class News_show extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		java.util.List<Stu_news> li = FindNewsUtil.findNewsAll();
		request.getSession().setAttribute("list", li);
		System.out.println("信息查找全部准备跳转");
		response.sendRedirect("web_file/News_show.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
