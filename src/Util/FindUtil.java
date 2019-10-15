package Util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import 获取账户信息.Stu_scores;
import 连接工具类.JDBCUtil;

public class FindUtil {
	public static List find(String key,String value) {
		
		List<Stu_scores> li = new ArrayList<Stu_scores>();
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from scores where " + key + " like '%" + value +"%'" + " order by zhuanye DESC,id,banji";
			System.out.println("条件查找执行的sql语句" + sql);
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println("对的");
				Stu_scores stu = new Stu_scores();
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setZhuanye(rs.getString("zhuanye"));
				stu.setBanji(rs.getString("banji"));
				stu.setChinese(rs.getString("chinese"));
				stu.setMath(rs.getString("math"));
				stu.setEnglish(rs.getString("english"));
				stu.setPe(rs.getString("pe"));
				
				li.add(stu);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
		return li;	
	}
	public static List<Stu_scores> findAll(){
		List<Stu_scores> li = new ArrayList<Stu_scores>();
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from scores order by zhuanye DESC,id,banji ";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Stu_scores stu = new Stu_scores();
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setZhuanye(rs.getString("zhuanye"));
				stu.setBanji(rs.getString("banji"));
				stu.setChinese(rs.getString("chinese"));
				stu.setMath(rs.getString("math"));
				stu.setEnglish(rs.getString("english"));
				stu.setPe(rs.getString("pe"));
				
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
