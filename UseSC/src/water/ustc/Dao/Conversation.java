package water.ustc.Dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.DocumentException;

import water.ustc.bean.Classxml;
import water.ustc.bean.Column;
import water.ustc.bean.OrMapping;
import water.ustc.bean.User;

public class Conversation {
//	public static Conversation conversation;
//	public static synchronized Conversation getInstance() {
//		if (conversation == null) {
//			conversation = new Conversation();	
//		}
//		return conversation;
//	}
//	private OrMapping orMapping;
//	public void setOrMapping(OrMapping orMapping) {
//		this.orMapping = orMapping;
////		System.out.println(orMapping);
//	}
//	
//	public OrMapping getOrMapping() {
//		return orMapping;
//	}
	OrMapping orMapping;
 public User query(String name) throws IllegalAccessException, InvocationTargetException, DocumentException {
	
	 Configuration configuration=new Configuration();
	 orMapping=configuration.readOrMapping();
	 System.out.println("连接query");
	 System.out.println("orMapping" +orMapping);
	 String sql="select * from my where userName=?";
	 Jdbcopen jdbcopen=new Jdbcopen();
	 List<User> list=jdbcopen.query(sql, new Object[]{name}, User.class);
	 System.out.println("list.get(0)"+list.get(0));
	 return (list!=null&&list.size()>0?list.get(0):null);
	 
 }
 @SuppressWarnings("finally")
public boolean insert(User user) throws IllegalAccessException, InvocationTargetException, DocumentException {
	User user1=this.query(user.getUserName());
	boolean result=false;
	if(user1==null) {
		Object[] paramsValue;
		String sql;
		try {
			paramsValue=new Object[20];
			sql="insert into ";
			List<Classxml> classxmls=orMapping.getClassxml();
			for(Classxml classxml:classxmls) {
				if(classxml.getName().equals("User"))
				{
					//sql=null;
					System.out.println("111111111111");
					sql=sql+classxml.getTable()+"";
					System.out.println("sql:"+sql);
					Field[] fields = user.getClass().getDeclaredFields();//获得某个类的所有声明字段但是不包括父类的声明字段
					for(Field f:fields) {
						System.out.println(f);
					}
					Field field=null;
					sql=sql+"(";
					System.out.println("sql:"+sql);
					for(int i=0;i<fields.length;i++) {
						field=fields[i];
						field.setAccessible(true);
						for( int j=0;j<classxml.getColumn().size();j++) {
							Column column=classxml.getColumn().get(j);
							if(column.getName().equals(field.getName())) {
								paramsValue[i]=field.get(user);
								//System.out.println("params"+paramsValue[i]);
								sql=sql + column.getColumn();
								if(j!=classxml.getColumn().size()-1) {
									sql=sql+",";
									System.out.println("sql:"+sql);
								}else {
									sql= sql + ")";
									System.out.println("sql:"+sql);
								}
							}
						}
						
					}
					sql=sql + "values " + "(";
					System.out.println("sql:"+sql);
					for(int i=0;i<fields.length;i++) {
						if(i!=fields.length-1) {
							sql=sql+"?";
							sql= sql+",";
						}
						else {
							sql=sql.substring(0,sql.length()-1);
							sql=sql+ ")";
						}
										
					}
					sql=sql+";";		
				}
				System.out.println("sql:" +sql);
				result=new Jdbcopen().update(sql, paramsValue);
			}			
		}
	finally {
		return result;
	}
	 
  }
	return result;
 }
 public boolean update(User user) throws SQLException {
//	 baseConfigCreateTable();
	 String sql="update my set userName=?,userPass=?";
	 Object[] paramsValue= {0,user.getUserName(),user.getUserPass()};
	 new Jdbcopen().update(sql, paramsValue);
	return true; 
 }
 public boolean delete(User user) throws SQLException {
//	 baseConfigCreateTable();
	 String sql="delete from my where userName=?";
	 Object[] paramsValue= {0,user.getUserName()};
	 new Jdbcopen().update(sql, paramsValue);
	return true;
	 
 }
// public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, DocumentException, SQLException {
//	 Conversation conversation=new Conversation();
//	 User user=new User();
//	 user.setUserName("111");
//	 user.setUserPass("333");
//	 conversation.query(user.getUserName());
//
//	 
// }
}
