package 获取账户信息;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FindUtil;
import 连接工具类.JDBCUtil;

public class Reward extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String jiangli = request.getParameter("jiangli");
		String chengfa = request.getParameter("chufa");
		String describe = request.getParameter("describe");
		String other = request.getParameter("other");
		
		
		if(name != "") {
			Connection coon = null;
			Statement st = null;
			try {
				coon = JDBCUtil.getConn();
				st =  coon.createStatement();
				String sql = "insert into reward values('"+ name +"','" + jiangli  +"','" + chengfa +"','" + describe +"','" + other + "')";
				int result = st.executeUpdate(sql);
				if(result > 0) {
						System.out.println("添加成功");
						System.out.println(name +"---" + other);
						response.setStatus(302);
						response.sendRedirect("web_file/Reward.html");
				}else {
					System.out.println("添加失败");
					response.setStatus(302);
					response.sendRedirect("web_file/Reward_news.html");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.release(coon, st);
			}
			
		}else {
			System.out.println("添加失败");
			response.setStatus(302);
			response.sendRedirect("web_file/Reward_news.html");
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
