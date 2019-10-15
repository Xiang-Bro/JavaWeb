package ��ȡ�˻���Ϣ;

import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.mysql.jdbc.Util;

import Util.Jiami;
import ���ݿ�����.JDBC_Conn;
import ���ӹ�����.JDBCUtil;
//���Ӳ����½���� 
public class Login extends HttpServlet {
	static String  admin = null;
	static String  mima = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset = UTF-8");
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String newpassword = Jiami.md5(password);
		
		admin = name;
		mima = newpassword;
		
		
		System.out.println(admin);
		System.out.println(newpassword);
		
		Connection coon = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			String sql = "select * from user_news where username= ? and password = ?" ;
			st =coon.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, newpassword);
			rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("��½�ɹ�");
				response.setStatus(302);
				
				String fiSql = "select quanxian from email where username = ?";
				st = coon.prepareStatement(fiSql);
				st.setString(1, name);
				rs = st.executeQuery();
				if(rs.next()) {
					//��ȡ�õ�½�û���Ȩ��
					String quanxian = rs.getString("quanxian");
					//�ж�Ȩ�޲�ͬ��ͨ���ض���ʽ��ת����ӦȨ�޵�������
					if(quanxian.equals("big")) {
						System.out.println("���û�");
						response.sendRedirect("web_file/Home.html");
					}else {
						System.out.println("С��");
						response.sendRedirect("web_file/Home_little.html");
					}
				}
				/*if(name.equals("admin")) {
					System.out.println("���û�");
					response.sendRedirect("web_file/Home.html");
				}else{
					System.out.println("С��");
					response.sendRedirect("web_file/Home_little.html");
				}*/
				
				
				//�����û�����½
					String sql2 = "delete from user_news where  username='" + name + "'";
					int result = st.executeUpdate(sql2);
					if(result > 0) {
						System.out.println("�˺�����ɾ���ɹ�");
					}else {
						System.out.println("�˺�����ɾ��ʧ��");
					}
					
			}else {
				System.out.println("��½ʧ��");
				//??��η�������
				response.getWriter().write("��Ϣ����");
				response.setStatus(302);
				response.sendRedirect("web_file/Login.html");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	
	/*public static String getName() {
		
		return admin;
		
	}*/
}