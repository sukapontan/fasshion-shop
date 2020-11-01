package model;

import java.util.ArrayList;
import java.util.Scanner;

import dao.EarningsDAO;
import dao.StockDAO;
import dao.StockOrderDAO;
import dao.userDAO;
import entity.StockOrderEntity;
import entity.UserEntity;

public class Admin {

	//
	public static void adminOpe(UserEntity user) {
		UserEntity user1 = user;
		Scanner sc = new Scanner(System.in);

		System.out.println("作業内容を選択してください。");
		System.out.println("1:人員確認");
		System.out.println("2:人員配置");
		System.out.println("3:発注承認");
		System.out.println("4:売上確認");

		// 入力した値を変数に格納
		int operation = sc.nextInt();

		// 入力した値で処理を分岐
		switch (operation) {
		case 1:
			personalCheck(user1);
			break;
		case 2:
			personalPlacement(user1);
			break;
		case 3:
			approval(user1);
			break;
		case 4:
			salesonfirmation(user1);
			break;
		}
	}

	// 人員確認に関する処理
	public static void personalCheck(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		userDAO dao = new userDAO();
		System.out.println("人員配置を確認する店舗を選択してください。");
		System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		int branch_id = sc.nextInt();

		switch (branch_id) {
		case 1:
			// L.A支店の人員構成を表示
			System.out.println("L.A支店の人員構成");
			dao.personnelConfirmation(branch_id);
			break;
		case 2:
			// 埼玉国スカ支店の人員構成を表示
			System.out.println("埼玉国スカ支店の人員構成");
			dao.personnelConfirmation(branch_id);
			break;
		case 3:
			// 赤坂支店の人員構成を表示
			System.out.println("赤坂支店の人員構成");
			dao.personnelConfirmation(branch_id);
			break;
		}

		System.out.println("続けて人員配置を変更しますか？\n1:はい\n2:いいえ");
		int answer = sc.nextInt();
		// 人員確認後、配置を変更する場合は、人員配置のメソッド呼び出し
		if (answer == 1) {
			personalPlacement(user);
		}
	}

	// 人員配置に関する処理
	public static void personalPlacement(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		userDAO dao = new userDAO();
		System.out.println("配置を変更する人員を入力してください。");
		String employeeName = sc.next();
		System.out.println("選択した人員が異動する店舗を選択してください。");
		System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		int branch_id = sc.nextInt();
		System.out.println("選択した従業員と移動先の店舗は以下になります。");

		switch (branch_id) {
		case 1:
			System.out.println("従業員名：" + employeeName);
			System.out.println("店舗：L.A支店");
			break;
		case 2:
			System.out.println("従業員名：" + employeeName);
			System.out.println("店舗：埼玉国スカ支店");
			break;
		case 3:
			System.out.println("従業員名：" + employeeName);
			System.out.println("店舗：赤坂支店");
			break;
		}

		System.out.println("人員配置を変更します。よろしいですか？\n1:はい\n2:いいえ");
		int answer = sc.nextInt();

		if (answer == 1) {
			int result = dao.staffing(branch_id, employeeName);
			if (result == 1) {
				System.out.println("人員の配置を変更しました。");
			}
		} else {
			personalPlacement(user);
		}

	}

	// 在庫発注承認に関する処理
	public static void approval(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		StockOrderDAO dao = new StockOrderDAO();
		StockDAO stockDao = new StockDAO();
		System.out.println("発注状況を確認する支店を選択してください。");
		System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		int branch_id = sc.nextInt();
		String branch = null;
		if (branch_id == 1) {
			branch = "L・A支店";
		} else if (branch_id == 2) {
			branch = "埼玉国スカ支店";
		} else if (branch_id == 3) {
			branch = "赤坂支店";
		}

		// 在庫の発注を表示する
		System.out.println(branch + "の発注状況");
		//在庫の発注情報が表示出来てないため、修正中
		dao.productOrderCheck(branch_id);

		// 発注を承認するか確認
		System.out.println("上記の発注を承認しますか？");
		System.out.println("1:はい\n2:いいえ");
		int answer = sc.nextInt();
		if (answer == 1) {
			//STOCKORDERテーブルから発注数を取得する
			ArrayList<StockOrderEntity> list = new ArrayList<StockOrderEntity>();
			list = dao.getQuantity(branch_id);

			//※修正中
			for(StockOrderEntity entity :list){
			int orderQuantity = entity.getOrder_quantity();
			String color = entity.getColor();
			String size = entity.getSize();
			int price = entity.getPrice();
			//STOCKORDERテーブルのステータスを更新する
			dao.orderApproval(branch_id,orderQuantity,color,size);
			//STOCKテーブルの在庫を更新する
			stockDao.orderUpdStock(branch_id, orderQuantity, color, size, price);
			}

		} else if (answer == 2) {
			System.out.println("発注承認を続けますか？");
			System.out.println("1:はい\n2:いいえ");
			int answer2 = sc.nextInt();
			if (answer2 == 1) {
				approval(user);
			} else if (answer2 == 2) {
				System.exit(0);
			}
		}

		System.out.println("発注を承認しました。");

	}

	// 売上確認に関する処理
	public static void salesonfirmation(UserEntity user) {

		// 売り上げを確認したい支店を選択
		System.out.println("売り上げを確認したい店舗を選択してください。");
		System.out.println("0:全支店\n1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		Scanner sc = new Scanner(System.in);
		int branch_id = sc.nextInt();
		String branch = null;
		if (branch_id == 0) {
			branch = "全支店";
		} else if (branch_id == 1) {
			branch = "L・A支店";
		} else if (branch_id == 2) {
			branch = "埼玉国スカ支店";
		} else if (branch_id == 3) {
			branch = "赤坂支店";
		}

		// 選択した支店の売り上げを表示する
		EarningsDAO earningsDao = new EarningsDAO();
		int result = earningsDao.checkEarnings(branch_id);
		System.out.println(branch + "の合計売上金額は" + result + "円です。");

	}
}
