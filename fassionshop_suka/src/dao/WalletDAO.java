package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Constant;
import entity.UserEntity;
import model.Client;

public class WalletDAO {

	// ログインしたユーザーのウォレット残高を取得
	public int getWalletBalance(int user_id) {

		int walletBalance = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			String sql = "SELECT BALANCE FROM WALLET WHERE USER_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表からウォレット残高を取得
			rs.next();
			walletBalance = rs.getInt("balance");

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return walletBalance;
	}

	// 購入後のウォレット残高の更新処理
	public int updWallet(UserEntity user, int totalPrice) {

		int balance = 0;
		int updBalance = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			String sql = "SELECT BALANCE FROM WALLET WHERE USER_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getUser_id());

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表からウォレット残高を取得
			rs.next();
			balance = rs.getInt("balance");
			updBalance = balance - totalPrice;

			// 金額が不足している場合、チャージ処理に遷移する
			if (updBalance < 0) {
				System.out.println("ウォレット残高が不足しています。チャージしてください。");
				System.out.println("現在のウォレット残高：" + balance + "円");
				// ウォレットチャージに遷移
				updBalance = Client.walletCharge(user);
				// 商品選択に戻る
				Client.purchase(user);
				//System.exit(0);
			}

			// conn.setAutoCommit(false); // オートコミットはオフ

			// UPDATE文の準備
			String sql2 = "UPDATE WALLET SET BALANCE = ?, UPDATE_DATE = NOW() WHERE USER_ID = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			pStmt2.setInt(1, updBalance);
			pStmt2.setInt(2, user.getUser_id());

			// UPDATE文を実行する
			int result = pStmt2.executeUpdate();
			if (result == 0) {
				// ロールバックする
				conn.rollback();
				System.out.println("更新対象データが存在しません。");
			}

			// コミットする
			// conn.commit();

			pStmt.close();
			pStmt2.close();
			conn.close();

			// return updBalance;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updBalance;

	}

	// チャージ金額入力後の残高の更新処理
	public int updWalletCharge(UserEntity user, int charge) {

		int balance = 0;
		int updBalance = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			String sql = "SELECT BALANCE FROM WALLET WHERE USER_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getUser_id());

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表からウォレット残高を取得
			rs.next();
			balance = rs.getInt("balance");
			// ウォレット残高にチャージ金額を足す
			updBalance = balance + charge;

			// conn.setAutoCommit(false); // オートコミットはオフ

			// UPDATE文の準備
			String sql2 = "UPDATE WALLET SET BALANCE = ? ,UPDATE_DATE = NOW() WHERE USER_ID = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			pStmt2.setInt(1, updBalance);
			pStmt2.setInt(2, user.getUser_id());

			// UPDATE文を実行する
			pStmt2.executeUpdate();

			// コミットする
			// conn.commit();

			pStmt.close();
			pStmt2.close();
			conn.close();

			// return updBalance;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updBalance;

	}

}
