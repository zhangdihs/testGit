package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	static String driver;
	static String url;
	static String username;
	static String password;
	static {
		Properties props=new Properties();
		try {
			props.load(
					Connection.class.getClassLoader()
					.getResourceAsStream("E:\\eclipseworkspace\\UseSC\\mysql.properties")
					
					);
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(props!=null) {
			url=props.getProperty("url");
			driver=props.getProperty("driver");
			username=props.getProperty("username");
			password=props.getProperty("password");
			
		}
		try {
			Class.forName(driver);//加载驱动
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static Connection getConnection() throws SQLException {
	Connection connection=DriverManager.getConnection(url, username, password);//获得链接
	return connection;
	
}
public static boolean close(Connection conn,PreparedStatement stmt) {
	if(stmt!=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn!=null) {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return false;
	
	
}
public static void close(Connection conn,PreparedStatement stmt,ResultSet rs) {
	if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(stmt!=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn!=null) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
}
