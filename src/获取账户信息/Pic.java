package ��ȡ�˻���Ϣ;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FindUtil;
import ���ӹ�����.JDBCUtil;

/**
 * Servlet implementation class Find
 */
public class Pic extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String key = request.getParameter("selectKey");
		String value = request.getParameter("selectValue");
		
		if( !value.equals("����ȫ��") ) {
			System.out.println("���յ�����Ϊ"+ key + "===" + value);
			java.util.List<Stu_scores> li = FindUtil.find(key, value);
			request.getSession().setAttribute("list", li);
			System.out.println("��������׼����ת");
			response.sendRedirect("web_file/Zhupic.jsp");
		}else {
			java.util.List<Stu_scores> li = FindUtil.findAll();
			request.getSession().setAttribute("list", li);
			System.out.println("����ȫ��׼����ת");
			response.sendRedirect("web_file/Zhupic.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
