package entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	private int user_id;		//ユーザーID
	private int userType; 		//ユーザー種別
	private String userName;	//ユーザー名
	private String pass; 		//パスワード
	private int branch; 		// 支店
	private int balance;		//ウォレット残高

	// デフォルトコンストラクタ
	public UserEntity() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public UserEntity(int user_id, int userType, String userName, String pass, int branch) {
		this.user_id = user_id;			//ユーザーID
		this.userName = userName;		//ユーザ名
		this.pass = pass;				//パスワード
		this.userType = userType;		//ユーザー種別
		this.branch = branch;			//支店
			}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

}
