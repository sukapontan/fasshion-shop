package model;

import java.util.Scanner;

import dao.StockDAO;
import dao.StockOrderDAO;
import entity.UserEntity;

public class Employee {

	public static void employeeOpe(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		boolean endFlg = true;

		while (endFlg) {
			System.out.println("作業内容を選択してください。");
			System.out.println("1:在庫の確認をする");
			System.out.println("2:在庫の発注をする");
			System.out.println("上記以外:従業員操作を終了する");

			String operation = sc.next();

			if (operation.equals("1")) {
				stockConfirmation(user);
			} else if (operation.equals("2")) {
				stockOrder(user);
			} else{
				System.out.println(user.getUserName() + "さん。\nお疲れ様でした。");
				endFlg = false;
			}
			System.out.println("");
		}
	}

	// 在庫の確認に関する処理
	public static void stockConfirmation(UserEntity user) {

		StockDAO dao = new StockDAO();
		int branch_id = user.getBranch();

		// 所属している店舗の在庫を表示する
		dao.chkStock(branch_id);

	}

	/*
	 * // 在庫の確認に関する処理 public static void stockConfirmation(UserEntity user) {
	 *
	 * StockDAO dao = new StockDAO(); int branch_id = user.getBranch();
	 *
	 * // 所属している店舗の在庫を表示する switch (branch_id) { case 1: // L.A支店の在庫を表示
	 * dao.chkStock(branch_id); System.out.println("【L.A支店の在庫】"); break; case 2:
	 * // 埼玉国スカ支店の在庫を表示 System.out.println("【埼玉国スカ支店の商品在庫】");
	 * dao.chkStock(branch_id); break; case 3: // 赤坂支店の在庫を表示
	 * System.out.println("【赤坂支店の在庫】"); dao.chkStock(branch_id); break; }
	 *
	 * }
	 */

	// 在庫の発注に関する処理
	public static void stockOrder(UserEntity user) {

		StockDAO dao = new StockDAO();
		int branch_id = user.getBranch();
		Scanner sc = new Scanner(System.in);

		// 所属している店舗の在庫を表示する
		dao.chkStock(branch_id);

		/*
		 * // 所属している店舗の在庫を表示する switch (branch_id) { case 1: // L.A支店の在庫を表示
		 * dao.chkStock(branch_id); System.out.println("【L.A支店の在庫】"); break;
		 * case 2: // 埼玉国スカ支店の在庫を表示 System.out.println("【埼玉国スカ支店の商品在庫】");
		 * dao.chkStock(branch_id); break; case 3: // 赤坂支店の在庫を表示
		 * System.out.println("【赤坂支店の在庫】"); dao.chkStock(branch_id); break; }
		 */

		// 発注する商品の選択
		System.out.println("発注する商品の情報を入力してください。");
		System.out.println("商品名：");
		String product_name = sc.next();
		System.out.println("カラー：");
		String color = sc.next();
		System.out.println("サイズ：");
		String size = sc.next();
		System.out.println("数量：");
		int quantity = sc.nextInt();
		System.out.println("発注する商品の金額を入力してください。");
		int price = sc.nextInt();

		// 発注商品確認
		System.out.println("発注する商品は以下の内容でよろしいでしょうか？");
		System.out.println("商品名：" + product_name);
		System.out.println("カラー：" + color);
		System.out.println("サイズ：" + size);
		System.out.println("時価：" + price);
		System.out.println("数量：" + quantity);
		System.out.println("1：はい\n2：いいえ");
		int answer = sc.nextInt();

		// 「はい」の場合、DBに発注SQLを投げる処理へ
		if (answer == 1) {
			StockOrderDAO orderDao = new StockOrderDAO();
			int result = orderDao.productOrder(color, size, price, branch_id, quantity);
			System.out.println(result + "件の発注を完了いたしました。");
		} else {
			stockOrder(user);
		}

	}

}
