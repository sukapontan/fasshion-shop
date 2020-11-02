package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Constant;

public class EarningsDAO {

	// 売上情報登録に関する処理
	public int infoEarnings(int totalPrice, int branch_id) {

		// 実行結果件数
		int result = 0;

		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// INSERT文の準備
			String sql = "INSERT INTO EARNINGS VALUES(NULL,?,NOW(),?,0)";

			// オートコミットはオフ
			// conn.setAutoCommit(false);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, totalPrice);
			pStmt.setInt(2, branch_id);

			// INSERT文を実行
			result = pStmt.executeUpdate();

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 売上確認に関する処理
	public int checkEarnings(int branch_id) {

		String sql = null;
		PreparedStatement pStmt = null;
		int earnings = 0;
		int totalEanings = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			if (branch_id == 0) {
				// 全支店の売り上げを表示するSELECT文を準備
				sql = "SELECT * FROM EARNINGS";
				pStmt = conn.prepareStatement(sql);
			} else {
				sql = "SELECT * FROM EARNINGS WHERE BRANCH_ID = ?";
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, branch_id);
			}

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表からデータを取得
			while (rs.next()) {
				earnings = rs.getInt("earnings");
				totalEanings += earnings;
			}
			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}return totalEanings;

	}
}
