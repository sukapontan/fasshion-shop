package model;

import java.util.Scanner;

import dao.WalletDAO;
import dao.userDAO;
import entity.UserEntity;

public class login {

	public static void main(String[] args) {
		// loginクラスのテスト
		loginLogic();
	}

	public static void loginLogic() {

		Scanner sc = new Scanner(System.in);
		System.out.println("ユーザー名とパスワードを入力してください。");
		System.out.print("ユーザー名：");
		String userName = sc.next();
		System.out.print("パスワード：");
		String userPass = sc.next();
		int walletBalance= 0;

		// 本番用（DBにアクセスしてユーザ名とパスワードを取得）
		userDAO dao = new userDAO();
		WalletDAO wdao = new WalletDAO();
		UserEntity user = dao.userLogin(userName, userPass);
		if(user != null){
			walletBalance = wdao.getWalletBalance(user.getUser_id());
		}else{
			System.out.println("ユーザー名またはパスワードが間違っています。");
			loginLogic();
		}

		// 入力したユーザー名とパスワードが合っているか確認
		if (userName.equals(user.getUserName()) && userPass.equals(user.getPass())) {
			System.out.println("ようこそ" + user.getUserName() + "さん");
			System.out.println("Wallet残高：" + walletBalance + "円");
		}
		// ユーザ名またはパスワードが間違ってる場合、ログインからやり直す
		else {
			System.out.println("ユーザー名またはパスワードが間違っています。");
			loginLogic();
		}



		// 【従業員】、【管理者】、【顧客】の判定を行う
		switch (user.getUserType()) {
		case 1:
			Employee.employeeOpe(user);
			break;
		case 2:
			Admin.adminOpe(user);
			break;
		case 3:
			Client.clientOpe(user);
			break;
		}

	}

}
