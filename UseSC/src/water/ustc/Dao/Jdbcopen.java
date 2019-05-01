package water.ustc.Dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.DocumentException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import sc.ustc.dao.BaseDAO;
import water.ustc.bean.OrMapping;

public class Jdbcopen {
	private static String driver=null;
	private static String url=null;
	private static String username=null;
	private static String password=null;
	private Connection connection=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
  static {
	  Configuration configuration=new Configuration();//��ȡurl����Ϣ���㴫��
	  OrMapping orMapping = null;
	try {
		orMapping = configuration.readOrMapping();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(orMapping!=null) {
		  url=orMapping.getJdbc().getUrl();
		  username=orMapping.getJdbc().getUsername();
		  password=orMapping.getJdbc().getPassword();
		  driver=orMapping.getJdbc().getDriver(); 
		  System.out.println("url " +url+username+driver+password);
	  }
  }
	public boolean update(String sql,Object[] paramsValue) throws SQLException {
		boolean result=false;
		BaseDAO basedao=new BaseDAO();//�����಻��ʵ����һ������
		//System.out.println(driver);
		Connection connection=basedao.openDBConnection(driver,url, username, password);
		PreparedStatement ps = connection.prepareStatement(sql);
		int count=ps.getParameterMetaData().getParameterCount();//��ȡ��������
		System.out.println(count);
		if(paramsValue!=null&&paramsValue.length>0)
		{
			for(int i=0;i<count;i++) {
				ps.setObject(i+1, paramsValue[i+1]);
				System.out.println("paramsValue"+paramsValue[i]);
			}
		}
		int a=ps.executeUpdate();
		if(a==1)
		{
			result=true;
		}
		basedao.closeDBConnection(connection,ps);
		return result;
		
	}
	public <T> List<T> query(String sql,Object[] paramsValue,Class<T> clazz){
		List<T> list=new ArrayList<T>();
		T t=null;
		BaseDAO baseDAO=new BaseDAO();
		try {	
			System.out.println("driver:"+driver);
			 connection=baseDAO.openDBConnection(driver, url, username, password);
			 ps = connection.prepareStatement(sql);
			if(paramsValue!=null&&paramsValue.length>0) {
				for(int i=0;i<paramsValue.length;i++) {
					ps.setObject(i+1, paramsValue[i]);
				}
			}
			 rs = ps.executeQuery();
			 System.out.println("rs:"+rs);
			ResultSetMetaData rsMetaData=(ResultSetMetaData) rs.getMetaData();//�õ��������������Ϣ��
			int columncount=rsMetaData.getColumnCount();
			System.out.println(columncount);
			while(rs.next()) {
				t=clazz.newInstance();
				for(int i=0;i<columncount;i++) {
					String columnName=rsMetaData.getColumnName(i+1);//����е�����
					System.out.println("columnName:"+columnName);
					Object value=rs.getObject(columnName);
					BeanUtils.copyProperty(t, columnName, value);
				}
				list.add(t);
			}
			baseDAO.closeDBConnection(connection, ps, rs);	
			return list;	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return list;
				
		}
	

}
