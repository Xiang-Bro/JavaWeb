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
//���ӳɼ���ӽ���
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
		
		System.out.println("��õ�����" + zhuanye);
		System.out.println("-------------------------------");
		
		Connection coon = null;
		Statement st = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			
			String findSql = "select * from scores where id='" + id + "' and name = '" + name +"'";
			
			System.out.println("��ѯ��sql���" + findSql);
			System.out.println("��ѯ������" + zhuanye);
			System.out.println("-------------------------------");
			
			ResultSet rs = st.executeQuery(findSql);
			if (rs.next()) {
				String upSql = "update scores set chinese =" + chinese +", math =" + math + ",english =" + english + ",pe =" +pe +" where id=" + id +  "' and name ='" + name +"' and zhuanye='" + zhuanye + "' and banji="+banji;                         
				
				System.out.println("���µ�sql���" + upSql);
				System.out.println("���µ�����" + zhuanye);
				System.out.println("-------------------------------");
				
				int result = st.executeUpdate(upSql);
				if(result >0) {
					System.out.println("�ɼ����³ɹ�");
					response.setStatus(302);
					response.sendRedirect("web_file/Scores.html");
				}
				else {
					System.out.println("���˴���");
				}
			}else {
				String sql = "insert into scores values(" + id + ",'" + name +"','" + zhuanye +"'," + banji +"," + chinese +","+ math +","+ english +","+ pe +")";
				
				System.out.println("����ִ�е�sql���" + sql);
				
				int result = st.executeUpdate(sql);
				if( result > 0) {
					System.out.println("�ɼ�����ɹ�");
					response.setStatus(302);
					response.sendRedirect("web_file/Scores.html");
				}
				else {
					System.out.println("���˴���");
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
