package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import 连接工具类.JDBCUtil;

public class Insert_mima {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		Scanner scPass = new Scanner(System.in);
		String pass = scPass.next();
		
		String password = Jiami.md5(pass);
		
		Connection coon = null;
		Statement st = null;
		
		try {
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "insert into user_news values('" + name + "','" + password +"')";
			int result = st.executeUpdate(sql);
			if( result > 0 ) {
				System.out.println("添加用户登陆成功");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st);
		}
	}
	
	
}
