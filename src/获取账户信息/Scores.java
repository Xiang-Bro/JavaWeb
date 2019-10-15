package 获取账户信息;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 连接工具类.JDBCUtil;
//连接成绩添加界面
public class Scores extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String zhuanye = request.getParameter("zhuanye");
		String banji = request.getParameter("banji");
		String chinese = request.getParameter("chinese");
		String math = request.getParameter("math");
		String english = request.getParameter("english");
		String pe = request.getParameter("PE");
		
		if(chinese=="") {
			chinese = null;
		}
		if(math=="") {
			math = null;
		}
		if(english=="") {
			english = null;
		}
		if(pe=="") {
			pe = null;
		}
		
		System.out.println("获得的数据" + zhuanye);
		System.out.println("-------------------------------");
		
		Connection coon = null;
		Statement st = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			
			String findSql = "select * from scores where id='" + id + "' and name = '" + name +"'";
			
			System.out.println("查询的sql语句" + findSql);
			System.out.println("查询的数据" + zhuanye);
			System.out.println("-------------------------------");
			
			ResultSet rs = st.executeQuery(findSql);
			if (rs.next()) {
				String upSql = "update scores set chinese =" + chinese +", math =" + math + ",english =" + english + ",pe =" +pe +" where id=" + id +  "' and name ='" + name +"' and zhuanye='" + zhuanye + "' and banji="+banji;                         
				
				System.out.println("更新的sql语句" + upSql);
				System.out.println("更新的数据" + zhuanye);
				System.out.println("-------------------------------");
				
				int result = st.executeUpdate(upSql);
				if(result >0) {
					System.out.println("成绩更新成功");
					response.setStatus(302);
					response.sendRedirect("web_file/Scores.html");
				}
				else {
					System.out.println("错了错了");
				}
			}else {
				String sql = "insert into scores values(" + id + ",'" + name +"','" + zhuanye +"'," + banji +"," + chinese +","+ math +","+ english +","+ pe +")";
				
				System.out.println("插入执行的sql语句" + sql);
				
				int result = st.executeUpdate(sql);
				if( result > 0) {
					System.out.println("成绩插入成功");
					response.setStatus(302);
					response.sendRedirect("web_file/Scores.html");
				}
				else {
					System.out.println("错了错了");
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
