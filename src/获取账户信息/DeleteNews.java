package 获取账户信息;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FindNewsUtil;
import 连接工具类.JDBCUtil;

/**
 * Servlet implementation class DeleteNews
 */
public class DeleteNews extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		Connection coon = null;
		Statement st = null;
		
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "delete from stu where id ="+ id;
			int result = st.executeUpdate(sql);
			while( result > 0) {
				System.out.println("删除成功");
				response.setStatus(302);
				//删除成功之后重新查找所有信息，并将数据重新传输到信息展示界面
				java.util.List<Stu_news> li = FindNewsUtil.findNewsAll();
				request.getSession().setAttribute("list", li);
				System.out.println("信息删除成功准备跳转");
				response.sendRedirect("web_file/News_show.jsp");
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
