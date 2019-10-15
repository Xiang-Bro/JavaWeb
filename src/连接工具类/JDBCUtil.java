package ���ӹ�����;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;



public class JDBCUtil {

	public static void main(String[] args) {
		
	}
	static String driver = null;
	static String url = null;
	static String name = null;
	static String password = null;
	
	static {
		try {
			//�������ö���
			Properties properties = new Properties();
			
			//���ļ�
			InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			//����������
			properties.load(is);
			//��ȡ����
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			password = properties.getProperty("password");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ʵ�������ݿ������
	public static Connection getConn() {
		Connection coon = null;
		try {
			Class.forName(driver);
			coon = DriverManager.getConnection(url,name, password);//textΪ���ݿ�����rootΪ�û�����130423Ϊ���ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coon;
	}
	
	//�ṩ��������������Դ�ͷ�
	public static void release(Connection coon, Statement st, ResultSet rs) {
		closeRs(rs);
		closeSt(st);
		closeCoon(coon);
	}
	public static void release(Connection coon, PreparedStatement st, ResultSet rs) {
		closeRs(rs);
		closeSt(st);
		closeCoon(coon);
	}
	public static void release(Connection coon, Statement st) {
		
		closeSt(st);
		closeCoon(coon);
	}
	
	//�ͷ���Դ������˽��
	private static void closeRs(ResultSet rs) {
		try {
			if(rs != null) {
			rs.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			rs = null;
		}
	}
	private static void closeCoon(Connection coon) {
		try {
			if(coon != null) {
			coon.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			coon = null;
		}
	}
	private static void closeSt(Statement st) {
		try {
			if(st != null) {
			st.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			st = null;
		}
	}
	
}
