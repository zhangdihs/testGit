package water.ustc.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import water.ustc.bean.User;
import water.ustc.bean.UserBean;

public class LoginAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserBean userBean;
    User  user;
	public String handleLogin(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, DocumentException  {
//		String name=request.getParameter("name");
//		String password=request.getParameter("password");
//		System.out.println(name+" "+password);
//		User user=new User(name, password);//新建一个user对象
//		user.setUserName(name);
//		user.setUserPass(password);
		UserBean userBean=new UserBean();//新建一个UserBean对象
		System.out.println(user.getUserName()+"     "+user.getUserPass());
		boolean result=userBean.signIn(user);//调用UserBean中的方法signIn获得返回的结果
		System.out.println("返回结果 "+result);
		
		if(result) {
			
			return "success";
		}
		else {
			return "failure";
		}
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
