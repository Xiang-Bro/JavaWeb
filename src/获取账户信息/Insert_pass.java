package ��ȡ�˻���Ϣ;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���ӹ�����.JDBCUtil;

/**
 * Servlet implementation class Insert_pass
 */
public class Insert_pass extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection coon = null;
		Statement st = null;
		
		System.out.println(Login.admin + "--" + Login.mima);
		
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "insert into user_news values ('" + Login.admin + "','" + Login.mima +"')" ;
			int result = st.executeUpdate(sql);
			if(result > 0) {
				System.out.println("���뺯����" +Login.admin + "--" + Login.mima);
				System.out.println("ע���ɹ�");
				response.setStatus(302);
				response.sendRedirect("web_file/Login.html");
				
			}else {
				System.out.println("����ʧ��");
				//??��η�������
				response.getWriter().write("��Ϣ����");
				response.setStatus(302);
				response.sendRedirect("web_file/Newhome.html");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
