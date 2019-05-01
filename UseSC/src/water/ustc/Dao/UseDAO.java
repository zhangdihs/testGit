package water.ustc.Dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dom4j.DocumentException;

import sc.ustc.dao.BaseDAO;
import water.ustc.bean.User;

public class UseDAO extends BaseDAO {
//	 String driver="com.mysql.cj.jdbc.Driver";
//	 String dbname="ss";
//     String url="jdbc:mysql://localhost:3306/"+dbname;
//	 String username="root";
//	 String password="199607";
//	 private Connection ct = null;
//	 private PreparedStatement ps = null;
//	 private ResultSet rs = null;
Conversation conversation=new Conversation();
	
	public  User query(String username) throws IllegalAccessException, InvocationTargetException, DocumentException {
//		User user=new User();
//		try {
//			Connection ct = this.openDBConnection();
//			ps = ct.prepareStatement("select userPass from my where userName='"+username+"'");
//			rs = ps.executeQuery();
//			if(rs.next()){
////				User user=new User();
//				user.setUserName(username);
//				user.setUserPass(rs.getString(1));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//			this.closeDBConnection(ct,ps,rs);
//		}
		return conversation.query(username);  
	  }
	  public  boolean insert(User user) throws ClassNotFoundException, SQLException, IllegalAccessException, InvocationTargetException, DocumentException {
//		  User u = (User) this.query(user.getUserName());
//		  boolean b=false;
//		  if(u==null) {
//			 ct=this.openDBConnection();
//			 ps=ct.prepareStatement("insert into user (username,password) values ("
//						+"'"+user.getUserName()+"','"+user.getUserPass()+"')");
//			 int a = ps.executeUpdate();
//			 if(a==1){
//					b = true;
//				}
//			 
//		  }
//	  this.closeDBConnection(ct, ps);
//		return b;
//		  
//	  }
//	  public  boolean update(User user) {
//		  boolean b = false;
//		
//			User u = (User) this.query(user.getUserName());
//			if(u==null){
//				try {
//					ct = this.openDBConnection();
//					ps = ct.prepareStatement("insert into user (username,password) values ("
//							+"'"+user.getUserName()+"','"+user.getUserPass()+"')");
//					int a  = ps.executeUpdate();
//					if(a==1){
//						b = true;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally{
//					this.closeDBConnection(ct, ps);
//				}
//			}
			return  conversation.insert(user);
	
		  
	  }
	 public boolean update(User user) throws SQLException {
		 
		return conversation.update(user);
		 
	 }
	 public boolean delete(User user) throws SQLException {
		 
		return conversation.delete(user);
		 
	 }
//	  public boolean delect(User user){
//		  
//		return false;
//		  
//	  }
	

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		 UseDAO useDAO=new UseDAO();
//		User uer= useDAO.query("abc");
//		System.out.println(uer.getUserName()+" "+uer.getUserPass());
//		 
//		
//	}
//	@Override
//	public boolean delect(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean insert(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean update(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
