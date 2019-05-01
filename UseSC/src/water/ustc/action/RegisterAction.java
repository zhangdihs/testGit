package water.ustc.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import water.ustc.Dao.UseDAO;
import water.ustc.bean.User;

public class RegisterAction {
	public String handleRegister(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, DocumentException  {
		boolean result = false;
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		User user=new User();
		user.setUserId(name);
		user.setUserPass(password);
		UseDAO usedao=new UseDAO();
		try {
			 result = usedao.insert(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(name.isEmpty()||password.isEmpty()) {
//		return "failure";
//		}
//		else {
//			return "success";
//		}
	
		if(result) {
			return "success";
		}
		else {
			return "false";
		}
	}

	
}
