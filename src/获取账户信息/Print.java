package 获取账户信息;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FindNewsUtil;
import Util.FindRewardUtil;
import Util.FindUtil;

/**
 * Servlet implementation class Print_news
 */
public class Print extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		String option = request.getParameter("option");
		System.out.println(option);
		
		if(option.equals("成绩报表")) {
			System.out.println("come in...");
			java.util.List<Stu_scores> li = FindUtil.findAll();
			request.getSession().setAttribute("list", li);
			System.out.println("成绩查找全部准备跳转");
			response.sendRedirect("web_file/Print_scores.jsp");
		}else if(option.equals("信息报表")) {
			java.util.List<Stu_news> li = FindNewsUtil.findNewsAll();
			request.getSession().setAttribute("list", li);
			System.out.println("信息查找全部准备跳转");
			response.sendRedirect("web_file/Print_news.jsp");
		}else {
			java.util.List<Stu_reward> li = FindRewardUtil.findRewardAll();
			request.getSession().setAttribute("list", li);
			System.out.println("奖惩查找全部准备跳转");
			response.sendRedirect("web_file/Print_reward.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
