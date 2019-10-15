package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import 获取账户信息.Stu_news;
import 获取账户信息.Stu_scores;
import 连接工具类.JDBCUtil;

public class FindNewsUtil {
	public static List<Stu_news> findNewsAll(){
		List<Stu_news> li = new ArrayList<Stu_news>();
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from stu";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println("对的");
				Stu_news stu = new Stu_news();
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setZhuanye(rs.getString("zhuanye"));
				stu.setBanji(rs.getInt("banji"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				stu.setTel(rs.getString("tel"));
				stu.setBirthday(rs.getString("birthday"));
				stu.setQq(rs.getString("qq"));
				stu.setId_card(rs.getString("id_card"));
				
				li.add(stu);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
		return li;	
	}
}
