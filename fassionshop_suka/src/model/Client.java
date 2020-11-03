package model;

import java.util.Scanner;

import dao.EarningsDAO;
import dao.StockDAO;
import dao.WalletDAO;
import entity.UserEntity;

public class Client {

	// 顧客の操作選択処理
	public static void clientOpe(UserEntity user) {
		Scanner sc = new Scanner(System.in);

		System.out.println("手続きを選択してください。");
		System.out.println("1:商品購入");
		System.out.println("2:ウォレットチャージ");

		int select = sc.nextInt();

		if (select == 1) {
			purchase(user);
		} else if (select == 2) {
			walletCharge(user);
		}
	}

	// 購入に関する処理
	public static void purchase(UserEntity user) {

		Scanner sc = new Scanner(System.in);

		String branchName = null;
		String color = null;
		String size = null;
		String product_name = null;

		// 購入する支店を選択
		System.out.println("購入する支店を選択してください。");
		System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		int branch = sc.nextInt();

		if (branch == 1) {
			branchName = "L・A支店";
		} else if (branch == 2) {
			branchName = "埼玉国スカ支店";
		} else if (branch == 3) {
			branchName = "赤坂支店";
		} else {
			System.out.println("入力に誤りがあります。");
			purchase(user);
		}

		// 商品選択
		System.out.println("購入する商品を選択してください。\n※現在、取り扱っている商品はスカＴシャツのみとなります。");
		System.out.println("1:スカＴシャツ");
		int product_code = sc.nextInt();
		if (product_code == 1) {
			product_name = "スカＴシャツ";
		} else {
			System.out.println("入力に誤りがあります。支店選択からやり直します。");
			purchase(user);
		}

		// カラー選択
		System.out.println("購入する商品のカラーを選択ください。");
		System.out.println("カラー\n1:赤\n2:緑\n3:黒");
		int colorSelect = sc.nextInt();

		if (colorSelect == 1) {
			color = "赤";
		} else if (colorSelect == 2) {
			color = "緑";
		} else if (colorSelect == 3) {
			color = "黒";
		} else {
			System.out.println("入力に誤りがあります。支店選択からやり直します。");
			purchase(user);
		}

		// サイズ選択
		System.out.println("購入する商品のサイズを選択ください。");
		System.out.println("1:S\n2:M\n3:L");
		int sizeSelect = sc.nextInt();

		if (sizeSelect == 1) {
			size = "S";
		} else if (sizeSelect == 2) {
			size = "M";
		} else if (sizeSelect == 3) {
			size = "L";
		} else {
			System.out.println("入力に誤りがあります。支店選択からやり直します。");
			purchase(user);
		}

		// 数量入力
		System.out.println("購入枚数を入力してください。");
		int number = sc.nextInt();

		// 購入内容確認
		System.out.println("お客様が選択した商品は以下の内容になります。");
		System.out.println("購入支店；" + branchName);
		System.out.println("カラー：" + color);
		System.out.println("サイズ：" + size);
		System.out.println("購入枚数：" + number);

		// 購入金額を表示後、購入意思確認
		StockDAO dao = new StockDAO();
		int price = dao.purchase(branch, color, size);
		int totalPrice = number * price;
		System.out.println("お支払金額は" + totalPrice + "円です。");
		System.out.println("購入しますか？");
		System.out.println("1：はい\n2：いいえ");

		int fix = sc.nextInt();

		// 「はい」の場合、STOCKテーブルとWALLETテーブルにUPDATE文、EARNINGSテーブルにINSERT文を投げる
		if (fix == 1) {
			// STOCK（在庫）テーブル更新処理
			dao.updStock(branch, color, size, number, user);
			// WALLET（ウォレット）テーブル更新処理
			WalletDAO walletdao = new WalletDAO();
			// EARNINGS（売上情報）テーブル更新処理
			EarningsDAO earningsDao = new EarningsDAO();
			earningsDao.infoEarnings(totalPrice, branch, product_name, number, color, size);

			// 商品購入後のウォレットの残高を取得
			int updBalance = walletdao.updWallet(user, totalPrice);

			System.out.println("購入が完了しました。");
			System.out.println("ウォレット残高：" + updBalance + "円");

			// 「いいえ」の場合、手続きの選択からやり直す
		} else if (fix == 2) {
			clientOpe(user);
		}
	}

	// ウォレットチャージに関する処理
	public static int walletCharge(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		WalletDAO dao = new WalletDAO();
		int updBalance = 0;

		// 入金する金額を入力指示
		System.out.println("チャージ金額を入力してください。");
		int charge = sc.nextInt();

		// 金額確認
		System.out.println("チャージ金額は以下でよろしいですか？");
		System.out.println("チャージ金額：" + charge + "円");
		System.out.println("1；はい\n2：いいえ");
		int answer = sc.nextInt();

		// 選択肢が「はい」の場合、チャージするメソッド呼び出し
		if (answer == 1) {
			updBalance = dao.updWalletCharge(user, charge);

			// チャージ後の金額を表示する
			System.out.println("チャージが完了しました。");
			System.out.println("ウォレット残高：" + updBalance + "円");

			// 選択肢が「いいえ」の場合、チャージ金額の入力に戻る
		} else if (answer == 2) {
			walletCharge(user);
		}
		return updBalance;
	}

}
