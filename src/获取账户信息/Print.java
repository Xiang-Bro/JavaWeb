package ��ȡ�˻���Ϣ;

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
		
		if(option.equals("�ɼ�����")) {
			System.out.println("come in...");
			java.util.List<Stu_scores> li = FindUtil.findAll();
			request.getSession().setAttribute("list", li);
			System.out.println("�ɼ�����ȫ��׼����ת");
			response.sendRedirect("web_file/Print_scores.jsp");
		}else if(option.equals("��Ϣ����")) {
			java.util.List<Stu_news> li = FindNewsUtil.findNewsAll();
			request.getSession().setAttribute("list", li);
			System.out.println("��Ϣ����ȫ��׼����ת");
			response.sendRedirect("web_file/Print_news.jsp");
		}else {
			java.util.List<Stu_reward> li = FindRewardUtil.findRewardAll();
			request.getSession().setAttribute("list", li);
			System.out.println("���Ͳ���ȫ��׼����ת");
			response.sendRedirect("web_file/Print_reward.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
