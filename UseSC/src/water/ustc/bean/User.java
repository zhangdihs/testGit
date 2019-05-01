package water.ustc.bean;

public class User {
	private String userId;
	private String userName;
	private String userPass;
	
//	public User(String userName, String userPass) {
//		super();
//		this.userName = userName;
//		this.userPass = userPass;
//	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
