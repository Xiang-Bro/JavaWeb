package ��ȡ�˻���Ϣ;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.GenerateCode;
import Util.SendEmail;

/**
 * Servlet implementation class SendMailBox
 */
public class SendMailBox extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String code = GenerateCode.getCode();
		/**
		 * ������֤�������
		 */
		String to = request.getParameter("mailBox");
		SendEmail.sendEmail(to, code);
		request.getSession().setAttribute(to, code);
		response.getWriter().print("��֤���Ѿ��ɹ����͵�����������");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
