package 获取账户信息;

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

import 连接工具类.JDBCUtil;
//连接插入信息界面 Insert_news
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
			
			System.out.println("查找的sql语句" + findSql);
			System.out.println("------------------------------------------");
			
			ResultSet rs = st.executeQuery(findSql);
			if(rs.next()) {
				//该姓名学生已经存在，则进行数据更新update
				String upSql = "update stu set sex = '" + sex + "',age='" + age + "',tel='" + tel + "',address='" + address + "',id_card='" + id_card + "',qq='" + qq + "',birthday='" + birthday + "',zhuanye='" + zhuanye + "',banji='" + banji + "' where name = '" + name +"'" ; 
				
				System.out.println("更新的sql语句" + upSql);
				System.out.println("------------------------------------------");
				
				int result = st.executeUpdate(upSql);
				if(result > 0) {
					System.out.println("信息更新成功");
					response.setStatus(302);						
					response.sendRedirect("web_file/Insert_news.html");
				}else {
					System.out.println("信息更新失败");
					response.setStatus(302);
					response.sendRedirect("web_file/Insert_news.html");
				}
			
			}else {
				//该姓名学生不存在，则添加新信息insert
				System.out.println("我要添加新信息喽");
				//获取数据库中最大id值
				String idsql = "select MAX(id) from stu";
				ResultSet idRs = st.executeQuery(idsql);
				if(idRs.next()) {
					int  newId = Integer.parseInt(idRs.getString("MAX(id)")) + 1;
					System.out.println("-------------最大的" + newId);
					//插入
					
					String sql = "insert into stu values(" + newId +",'"+ name +"','" + zhuanye +"'," + banji +",'" + sex +"','" + age +"','" + tel + "','" + address  + "','" + birthday + "','" + qq +"','" +  id_card +"')";
					System.out.println("插入的sql语句" + findSql);
					System.out.println("------------------------------------------");
					
					int result = st.executeUpdate(sql);				
					if(result > 0) {
							System.out.println("信息添加成功");
							response.setStatus(302);						
							response.sendRedirect("web_file/Insert_news.html");
					}else {
						System.out.println("信息添加失败");
						response.setStatus(302);
						response.sendRedirect("web_file/Insert_news.html");
					}
				}
				
				/*//插入
				String sql = "insert into stu values(11,'"+ name +"','" + zhuanye +"'," + banji +",'" + sex +"','" + age +"','" + tel + "','" + address  + "','" + birthday + "','" + qq +"','" +  id_card +"')";
				
				System.out.println("插入的sql语句" + findSql);
				System.out.println("------------------------------------------");
				
				int result = st.executeUpdate(sql);				
				if(result > 0) {
						System.out.println("信息添加成功");
						response.setStatus(302);						
						response.sendRedirect("web_file/Insert_news.html");
				}else {
					System.out.println("信息添加失败");
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
