package water.ustc.bean;

import java.lang.reflect.InvocationTargetException;

import org.dom4j.DocumentException;

import water.ustc.Dao.UseDAO;

public class UserBean {
	private String userId;
	private String userName;
	private String userPass;

public boolean signIn(User user) throws IllegalAccessException, InvocationTargetException, DocumentException {
	User user1=new User();
	User user2=new User();
		user1=user;
		userName=user1.getUserName();
		userPass=user1.getUserPass();
		System.out.println("userName:"+userName);
		System.out.println("userpass:"+userPass);
		UseDAO usedao=new UseDAO();
		user2=(User) usedao.query(userName);
		if(user2.getUserPass().equals(userPass))
				{
			return true;
		}
		else {
			return false;
			}
}

}
