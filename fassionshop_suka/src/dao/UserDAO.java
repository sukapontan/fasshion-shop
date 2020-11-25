
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Constant;
import entity.UserEntity;

public class UserDAO {

	// ログインするユーザーの情報を取得する処理
	public UserEntity userLogin(String userName, String userPass) {

		UserEntity loginUser = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			String sql = "SELECT * FROM USER WHERE USER.NAME = ? AND USER.PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			pStmt.setString(2, userPass);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 一致したユーザーが存在した場合、そのユーザーを表すUserインスタンスを生成
			if (rs.next()) {
				// 結果表からデータを取得
				int userId = rs.getInt("id"); // ユーザーID
				int userType = rs.getInt("userType"); // ユーザー種別
				String name = rs.getString("name"); // 名前
				String pass = rs.getString("pass"); // パスワード
				int branch_id = rs.getInt("branch_id"); // 支店ID
				String branch_name = rs.getString("branch_name");//支店名

				loginUser = new UserEntity(userId, userType, name, pass, branch_id, branch_name);

				pStmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginUser;
	}

	// 人員確認に関する処理
	public ArrayList<UserEntity> personnelConfirmation(int branch_id) {

		ArrayList<UserEntity> list = new ArrayList<UserEntity>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			String sql = "SELECT NAME FROM USER WHERE BRANCH_ID = ? AND USERTYPE = 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				UserEntity entity = new UserEntity();
				entity.setUserName(rs.getString("name"));

				list.add(entity);
			}
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 人員配置に関する処理
	public int staffing(int branch_id,String branch_name, String name) {

		int result = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// 人員配置を変更するUPDATE文の準備
			String sql = "UPDATE USER SET BRANCH_ID = ?, BRANCH_NAME = ? WHERE NAME = ? AND USERTYPE = 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);
			pStmt.setString(2, branch_name);
			pStmt.setString(3, name);

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
