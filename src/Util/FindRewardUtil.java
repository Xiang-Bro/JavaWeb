package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import 获取账户信息.Stu_reward;
import 获取账户信息.Stu_scores;
import 连接工具类.JDBCUtil;

public class FindRewardUtil {
	public static List<Stu_reward> findRewardAll(){
		List<Stu_reward> li = new ArrayList<Stu_reward>();
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from reward order by id ";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Stu_reward stu = new Stu_reward();
				stu.setId(rs.getString("id"));
				stu.setName(rs.getString("name"));
				stu.setJiangli(rs.getString("jiangli"));
				stu.setChengfa(rs.getString("chengfa"));
				stu.setDescribe(rs.getString("describe"));
				stu.setOther(rs.getString("other"));
				
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
