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
//		User user=new User(name, password);//�½�һ��user����
//		user.setUserName(name);
//		user.setUserPass(password);
		UserBean userBean=new UserBean();//�½�һ��UserBean����
		System.out.println(user.getUserName()+"     "+user.getUserPass());
		boolean result=userBean.signIn(user);//����UserBean�еķ���signIn��÷��صĽ��
		System.out.println("���ؽ�� "+result);
		
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
