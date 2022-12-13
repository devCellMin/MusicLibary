package UserSetting;

public class UserBuilder {
	Userset user=  new Userset();
	
	public UserBuilder(Userset user) {
		super();
		this.user = user;
	}
	
	public Userset getUser() {
		return this.user;
	}
	
	public UserBuilder userId(String userId) {
		user.setUserId(userId);
		return this;
	}
	public UserBuilder userName(String userName) {
		user.setUserName(userName);
		return this;
	}
	public UserBuilder userPWD(String userPWD) {
		user.setUserPWD(userPWD);
		return this;
	}
	public UserBuilder userAge(int userAge) {
		user.setUserAge(userAge);
		return this;
	}
	public UserBuilder userGender(String userGender) {
		user.setUserGender(userGender);
		return this;
	}
	public UserBuilder userPhoneNum(String userPhoneNum) {
		user.setUserPhoneNum(userPhoneNum);
		return this;
	}
	public UserBuilder userAddr(String userAddr) {
		user.setUserAddr(userAddr);
		return this;
	}
	public UserBuilder userType(String userType) {
		user.setUserType(userType);
		return this;
	}
	
	
	
	
}
