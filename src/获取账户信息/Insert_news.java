package ��ȡ�˻���Ϣ;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import ���ӹ�����.JDBCUtil;
//���Ӳ�����Ϣ���� Insert_news
/**
 * Servlet implementation class Change
 */
public class Insert_news extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset = UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("username");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String id_card = request.getParameter("id_card");
		String qq = request.getParameter("qq");
		String birthday = request.getParameter("birthday");
		String zhuanye = request.getParameter("zhuanye");
		int banji = Integer.parseInt(request.getParameter("banji"));
		
		
		Connection coon = null;
		Statement st = null;
		try {				
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			
			String findSql = "select * from stu where name = '" + name + "'";
			
			System.out.println("���ҵ�sql���" + findSql);
			System.out.println("------------------------------------------");
			
			ResultSet rs = st.executeQuery(findSql);
			if(rs.next()) {
				//������ѧ���Ѿ����ڣ���������ݸ���update
				String upSql = "update stu set sex = '" + sex + "',age='" + age + "',tel='" + tel + "',address='" + address + "',id_card='" + id_card + "',qq='" + qq + "',birthday='" + birthday + "',zhuanye='" + zhuanye + "',banji='" + banji + "' where name = '" + name +"'" ; 
				
				System.out.println("���µ�sql���" + upSql);
				System.out.println("------------------------------------------");
				
				int result = st.executeUpdate(upSql);
				if(result > 0) {
					System.out.println("��Ϣ���³ɹ�");
					response.setStatus(302);						
					response.sendRedirect("web_file/Insert_news.html");
				}else {
					System.out.println("��Ϣ����ʧ��");
					response.setStatus(302);
					response.sendRedirect("web_file/Insert_news.html");
				}
			
			}else {
				//������ѧ�������ڣ����������Ϣinsert
				System.out.println("��Ҫ�������Ϣ�");
				//��ȡ���ݿ������idֵ
				String idsql = "select MAX(id) from stu";
				ResultSet idRs = st.executeQuery(idsql);
				if(idRs.next()) {
					int  newId = Integer.parseInt(idRs.getString("MAX(id)")) + 1;
					System.out.println("-------------����" + newId);
					//����
					
					String sql = "insert into stu values(" + newId +",'"+ name +"','" + zhuanye +"'," + banji +",'" + sex +"','" + age +"','" + tel + "','" + address  + "','" + birthday + "','" + qq +"','" +  id_card +"')";
					System.out.println("�����sql���" + findSql);
					System.out.println("------------------------------------------");
					
					int result = st.executeUpdate(sql);				
					if(result > 0) {
							System.out.println("��Ϣ��ӳɹ�");
							response.setStatus(302);						
							response.sendRedirect("web_file/Insert_news.html");
					}else {
						System.out.println("��Ϣ���ʧ��");
						response.setStatus(302);
						response.sendRedirect("web_file/Insert_news.html");
					}
				}
				
				/*//����
				String sql = "insert into stu values(11,'"+ name +"','" + zhuanye +"'," + banji +",'" + sex +"','" + age +"','" + tel + "','" + address  + "','" + birthday + "','" + qq +"','" +  id_card +"')";
				
				System.out.println("�����sql���" + findSql);
				System.out.println("------------------------------------------");
				
				int result = st.executeUpdate(sql);				
				if(result > 0) {
						System.out.println("��Ϣ��ӳɹ�");
						response.setStatus(302);						
						response.sendRedirect("web_file/Insert_news.html");
				}else {
					System.out.println("��Ϣ���ʧ��");
					response.setStatus(302);
					response.sendRedirect("web_file/Insert_news.html");
				}*/
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
