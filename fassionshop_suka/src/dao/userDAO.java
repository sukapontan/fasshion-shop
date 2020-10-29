package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserEntity;

public class userDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/fashionshop_suka";
	private final String DB_USER = "root";
	private final String DB_PASS = "Sukapontan0303";

	// ログインするユーザーの情報を取得する処理
	public UserEntity userLogin(String userName, String userPass) {

		UserEntity loginUser = new UserEntity();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT ID FROM USER WHERE NAME = ?";
			PreparedStatement pStmt1 = conn.prepareStatement(sql);
			pStmt1.setString(1, userName);
			ResultSet rs1 = pStmt1.executeQuery();
			rs1.next();
			int id = rs1.getInt("id");

			String sql2 = "SELECT USER.ID,USER.USERTYPE,USER.NAME,USER.PASS,USER.BRANCH_ID,WALLET.BALANCE FROM USER,WALLET WHERE USER.NAME = ? AND USER.PASS = ? AND WALLET.USER_ID = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt2.setString(1, userName);
			pStmt2.setString(2, userPass);
			pStmt2.setInt(3, id);

			// SELECT文を実行
			ResultSet rs = pStmt2.executeQuery();

			// 一致したユーザーが存在した場合、そのユーザーを表すUserインスタンスを生成
			if (rs.next()) {
				// 結果表からデータを取得
				int userId = rs.getInt("id"); // ユーザーID
				int userType = rs.getInt("userType"); // ユーザー種別
				String name = rs.getString("name"); // 名前
				String pass = rs.getString("pass"); // パスワード
				int branch_id = rs.getInt("branch_id"); // 支店ID
				int balance = rs.getInt("balance"); // ウォレット残高

				loginUser = new UserEntity(userId, userType, name, pass, branch_id, balance);

				pStmt1.close();
				pStmt2.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return loginUser;
	}

	// 人員確認に関する処理
	public String personnelConfirmation(int branch_id) {

		// 配列で人員情報を表示できないか検討中 2020/10/19
		// ArrayList<UserEntity> userEntity = new ArrayList<UserEntity>();
		String employeeName = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT NAME FROM USER WHERE BRANCH_ID = ? AND USERTYPE = 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			rs.next();
			employeeName = rs.getNString("name");

			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeName;
	}

	// 人員配置に関する処理
	public int staffing(int branch_id, String name) {

		int result = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// 人員配置を変更するUPDATE文の準備
			String sql = "UPDATE USER SET BRANCH_ID = ? WHERE NAME = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);
			pStmt.setString(2, name);

			// SELECT文を実行
			result = pStmt.executeUpdate();

			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}