package model;

import java.util.ArrayList;
import java.util.Scanner;

import dao.EarningsDAO;
import dao.StockDAO;
import dao.StockOrderDAO;
import dao.UserDAO;
import entity.EarningsEntity;
import entity.StockOrderEntity;
import entity.UserEntity;

public class Admin {

	//
	public static void adminOpe(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		boolean endFlg = true;

		while (endFlg) {
			System.out.println("作業内容を選択してください。");
			System.out.println("1:人員確認");
			System.out.println("2:人員配置の変更");
			System.out.println("3:発注承認");
			System.out.println("4:売上確認");
			System.out.println("上記以外：管理者操作を終了する");

			// 入力した値を変数に格納
			String operation = sc.next();

			// 入力した値で処理を分岐
			switch (operation) {
			case "1":
				personalCheck(user);
				break;
			case "2":
				personalPlacement(user);
				break;
			case "3":
				approval(user);
				break;
			case "4":
				salesonfirmation(user);
				break;
			default:
				System.out.println("操作を終了します。");
				System.out.println(user.getUserName() + "さん。お疲れ様でした。");
				endFlg = false;
				break;
			}
		}
	}

	// 人員確認に関する処理
	public static void personalCheck(UserEntity user) {

		Scanner sc = new Scanner(System.in);
		UserDAO dao = new UserDAO();
		ArrayList<UserEntity> entity = null;
		System.out.println("人員配置を確認する店舗を選択してください。");
		System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
		int branch_id = sc.nextInt();

		switch (branch_id) {
		case 1:
			// L.A支店の人員構成を表示
			System.out.println("L.A支店の人員構成");
			entity = dao.personnelConfirmation(branch_id);
			for (UserEntity list : entity) {
				System.out.println(list.getUserName());
			}
			break;
		case 2:
			// 埼玉国スカ支店の人員構成を表示
			System.out.println("埼玉国スカ支店の人員構成");
			entity = dao.personnelConfirmation(branch_id);
			for (UserEntity list : entity) {
				System.out.println(list.getUserName());
			}
			break;
		case 3:
			// 赤坂支店の人員構成を表示
			System.out.println("赤坂支店の人員構成");
			entity = dao.personnelConfirmation(branch_id);
			for (UserEntity list : entity) {
				System.out.println(list.getUserName());
			}
			break;
		default:
			System.out.println("支店が存在しません。\n正しい支店番号を選択してください。");
			personalCheck(user);
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
		UserDAO dao = new UserDAO();
		ArrayList<UserEntity> entity = null;
		String employeeName = null;
		int changeBranch_id = 0;
		String changeBranchName = null;
		boolean personalCheck = true;

		while (personalCheck) {
			System.out.println("人員配置を確認する店舗を選択してください。");
			System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
			int branch_id = sc.nextInt();

			switch (branch_id) {
			case 1:
				// L.A支店の人員構成を表示
				System.out.println("【L.A支店の人員一覧】");
				entity = dao.personnelConfirmation(branch_id);
				for (UserEntity list : entity) {
					System.out.println(list.getUserName());
				}
				personalCheck = false;
				break;
			case 2:
				// 埼玉国スカ支店の人員構成を表示
				System.out.println("【埼玉国スカ支店の人員一覧】");
				entity = dao.personnelConfirmation(branch_id);
				for (UserEntity list : entity) {
					System.out.println(list.getUserName());
				}
				personalCheck = false;
				break;
			case 3:
				// 赤坂支店の人員構成を表示
				System.out.println("【赤坂支店の人員一覧】");
				entity = dao.personnelConfirmation(branch_id);
				for (UserEntity list : entity) {
					System.out.println(list.getUserName());
				}
				personalCheck = false;
				break;
			default:
				System.out.println("支店が存在しません。\n正しい支店番号を選択してください。");
				break;
			}
		}

		UserEntity entity2 = null;
		boolean personaChange = true;
		while (personaChange) {
			System.out.println("配置を変更する人員を入力してください。");
			employeeName = sc.next();
			UserDAO dao2 = new UserDAO();
			entity2 = dao2.RegisteredStoreCheck(employeeName);

			// 入力したユーザーがDB上に存在するかチェック
			if (entity2 == null) {
				System.out.println("入力したユーザーは存在しません。再度入力し直してください。");
				continue;
			}
			personaChange = false;
		}

		boolean shopExist = true;
		while (shopExist) {
			System.out.println("選択した人員が異動する店舗を選択してください。");
			System.out.println("1:L・A支店\n2:埼玉国スカ支店\n3:赤坂支店");
			changeBranch_id = sc.nextInt();
			// 選択内容確認
			switch (changeBranch_id) {
			case 1:
				changeBranchName = "L・A支店";
				// 選択した店舗が現在所属している店舗の場合、再度入力し直す
				if (entity2.getBranch_name().equals(changeBranchName)) {
					System.out.println("※選択した店舗は在籍店舗です。別の店舗を選択してください。");
				} else {
					System.out.println("選択した従業員と異動先の店舗は以下になります。");
					System.out.println("従業員名：" + employeeName);
					System.out.println("店舗：" + changeBranchName);
					shopExist = false;
				}
				break;
			case 2:
				changeBranchName = "埼玉国スカ支店";
				// 選択した店舗が現在所属している店舗の場合、再度入力し直す
				if (entity2.getBranch_name().equals(changeBranchName)) {
					System.out.println("※選択した店舗は在籍店舗です。別の店舗を選択してください。");
				} else {
					System.out.println("選択した従業員と異動先の店舗は以下になります。");
					System.out.println("従業員名：" + employeeName);
					System.out.println("店舗：" + changeBranchName);
					shopExist = false;
				}
				break;
			case 3:
				changeBranchName = "赤坂支店";
				// 選択した店舗が現在所属している店舗の場合、再度入力し直す
				if (entity2.getBranch_name().equals(changeBranchName)) {
					System.out.println("※選択した店舗は在籍店舗です。別の店舗を選択してください。");
				} else {
					System.out.println("選択した従業員と異動先の店舗は以下になります。");
					System.out.println("従業員名：" + employeeName);
					System.out.println("店舗：" + changeBranchName);
					shopExist = false;
				}
				break;
			default:
				System.out.println("支店が存在しません。\nもう一度やり直してください。");
				break;
			}
		}

		System.out.println("人員配置を変更します。よろしいですか？\n1:はい\n1以外:いいえ");
		int answer = sc.nextInt();

		if (answer == 1) {
			int result = dao.staffing(changeBranch_id, changeBranchName, employeeName);
			if (result == 1) {
				System.out.println("人員の配置を変更しました。");
			} else {
				System.out.println("人員配置の更新に失敗しました。\nもう一度最初からやり直して下さい。");
			}
		}
	}

	// 在庫発注承認に関する処理
	public static void approval(UserEntity user) {

		ArrayList<StockOrderEntity> list = null;
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
		list = dao.productOrderCheck(branch_id);

		for (StockOrderEntity entity : list) {
			System.out.print(" 商品名：" + entity.getProduct_name());
			System.out.print(" 価格：" + entity.getPrice());
			System.out.print(" カラー：" + entity.getColor());
			System.out.print(" サイズ：" + entity.getSize());
			System.out.print(" 支店コード：" + entity.getBranch_id());
			System.out.print(" 数量：" + entity.getOrder_quantity() + "\n");
		}

		// 発注履歴が存在する場合、発注承認の確認
		if (!(list.size() == 0)) {
			// 発注を承認するか確認
			System.out.println("上記の発注を承認しますか？");
			System.out.println("1:はい\n2:いいえ");
			int answer = sc.nextInt();
			if (answer == 1) {
				// STOCKORDERテーブルから発注数を取得する
				list = dao.getQuantity(branch_id);

				for (StockOrderEntity entity : list) {
					int orderQuantity = entity.getOrder_quantity();
					String color = entity.getColor();
					String size = entity.getSize();
					int price = entity.getPrice();
					// STOCKORDERテーブルのステータスを更新する
					dao.orderApproval(branch_id, orderQuantity, color, size);
					// STOCKテーブルの在庫を更新する
					stockDao.orderUpdStock(branch_id, orderQuantity, color, size, price);
				}

			} else if (answer == 2) {
				System.out.println("発注承認を続けますか？");
				System.out.println("1:はい\n2:いいえ");
				int answer2 = sc.nextInt();
				if (answer2 == 1) {
					approval(user);
				} else if (answer2 == 2) {
					System.out.println(user.getUserName() + "さん。\nお疲れ様でした。");
					System.exit(0);
				}
			}

			System.out.println("発注を承認しました。");

		} else {
			// 発注履歴が存在しない場合、発注承認をキャンセルし、作業内容選択に戻る
			System.out.println("承認対象データが見つかりません。");
			adminOpe(user);
		}

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
		System.out.println("【売上一覧】");
		EarningsDAO earningsDao = new EarningsDAO();
		ArrayList<EarningsEntity> result = earningsDao.checkEarnings(branch_id);

		int totalEarnings = 0;
		for (EarningsEntity entity : result) {
			System.out.print("商品名：" + entity.getProduct_name());
			System.out.print("　販売数：" + entity.getQuantity());
			System.out.print("　カラー：" + entity.getColor());
			System.out.print("　サイズ：" + entity.getSize());
			System.out.println("　売上金額：" + entity.getEarnings());
			totalEarnings += entity.getEarnings();
		}

		System.out.println("=================================================");
		System.out.println(branch + "の合計売上金額は" + totalEarnings + "円です。");
		System.out.println("=================================================");

	}
}
