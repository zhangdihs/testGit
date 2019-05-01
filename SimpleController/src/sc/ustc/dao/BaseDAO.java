package sc.ustc.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public  class  BaseDAO {
//	 String driver="com.mysql.cj.jdbc.Driver";
//	 String dbname="ss";
//     String url="jdbc:mysql://localhost:3306/ss?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
//	 String username="root";
//	 String password="199607";
//	private Connection ct = null;
//	private PreparedStatement ps = null;
//	private ResultSet rs = null;
//	static {
//		Properties props=new Properties();
//		try {
//			props.load(
//					Connection.class.getClassLoader()
//					.getResourceAsStream("E:\\eclipseworkspace\\UseSC\\mysql.properties")
//					
//					);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		if(props!=null) {
//			url=props.getProperty("url");
//			driver=props.getProperty("driver");
//			username=props.getProperty("username");
//			password=props.getProperty("password");
//			
//		}
//
//	}
	
public Connection  openDBConnection(String driver,String url,String username,String password) {
	try {
		Class.forName(driver);
		System.out.println("driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("no driver");
		e.printStackTrace();
	}//¼ÓÔØÇý¶¯
	Connection connection = null;
	try {
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("connection");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("no connection");
		e.printStackTrace();
	}
//		return JdbcUtil.getConnection();
	return connection;
		
	}
//  public abstract Object query(String sql);
//  public abstract boolean insert(String sql);
//  public abstract boolean update(String sql);
//  public abstract boolean delect(String sql);
	public  boolean closeDBConnection(Connection conn,PreparedStatement stmt) {
				try {
					
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	public  boolean closeDBConnection(Connection conn,PreparedStatement stmt,ResultSet rs) {
		try {
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		 BaseDAO useDAO=new BaseDAO();
//		useDAO.openDBConnection();		
//	}
}
