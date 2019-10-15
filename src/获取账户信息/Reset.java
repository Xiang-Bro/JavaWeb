package 获取账户信息;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.accessibility.AccessibleRelation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Jiami;
import 连接工具类.JDBCUtil;
//连接修改密码界面 Reset
public class Reset extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String newpassword = Jiami.md5(password);
		
		
		Connection coon = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			String sql = "select * from email where username= ? and email = ?" ;
			st = coon.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, email);
			rs = st.executeQuery();
			if(rs.next()) {
				String insql = "update user_news set password = ? where username = ?";
				st = coon.prepareStatement(insql);
				st.setString(1, newpassword);
				st.setString(2, name);
				int result = st.executeUpdate();
				if(result > 0) {
					System.out.println("修改密码成功");
					response.setStatus(302);
					response.sendRedirect("web_file/Login.html");
				}
			}else {
				
				//??如何返回数据
				response.getWriter().write("信息错误");
				response.setStatus(302);
				response.sendRedirect("web_file/Reset.html");
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

}
