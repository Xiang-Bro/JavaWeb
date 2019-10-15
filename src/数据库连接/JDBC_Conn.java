package 数据库连接;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import 连接工具类.JDBCUtil;

public class JDBC_Conn {

	public static void main(String[] args) {
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select MAX(id) from stu ";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString("MAX(id)");
				System.out.println("最大的值为" + id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
	}

}
