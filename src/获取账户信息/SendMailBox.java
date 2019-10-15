package 获取账户信息;

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
		 * 接收验证码的邮箱
		 */
		String to = request.getParameter("mailBox");
		SendEmail.sendEmail(to, code);
		request.getSession().setAttribute(to, code);
		response.getWriter().print("验证码已经成功发送到了您的邮箱");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
