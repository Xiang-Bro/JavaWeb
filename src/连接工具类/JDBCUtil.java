package 连接工具类;


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
			//创建配置对象
			Properties properties = new Properties();
			
			//读文件
			InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			//导入输入流
			properties.load(is);
			//读取属性
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			password = properties.getProperty("password");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//实现与数据库的连接
	public static Connection getConn() {
		Connection coon = null;
		try {
			Class.forName(driver);
			coon = DriverManager.getConnection(url,name, password);//text为数据库名，root为用户名，130423为数据库密码
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coon;
	}
	
	//提供公共方法进行资源释放
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
	
	//释放资源方法，私有
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
