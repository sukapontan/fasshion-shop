package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Constant;
import entity.StockEntity;
import entity.UserEntity;
import model.Client;

public class StockDAO {

	// 商品購入に関する処理
	public int purchase(int branch, String colorSelect, String sizeSelect) {

		int price = 0;
		int stockId = 0;
		int quantity = 0;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SELECT文の準備
			String sql = "SELECT * FROM STOCK WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch);
			pStmt.setString(2, colorSelect);
			pStmt.setString(3, sizeSelect);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表からデータを取得
			while (rs.next()) {
				price = rs.getInt("price");
				stockId = rs.getInt("stock_id");
				quantity = rs.getInt("quantity");
			}
			pStmt.close();
			conn.close();
			return price;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}

	// 購入後の在庫更新処理
	public void updStock(int branch, String color, String size, int number, UserEntity user) {

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SQL文の準備
			String sql = "SELECT QUANTITY FROM STOCK WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch);
			pStmt.setString(2, color);
			pStmt.setString(3, size);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			// 結果表から在庫数を取得
			rs.next();
			int quantity = rs.getInt("quantity");
			int updQuantity = quantity - number;

			// 在庫がない、または足りない時は商品選択に戻る
			if (updQuantity < 0) {
				System.out.println("在庫がありません。\n別の商品を選択してください。");
				// 商品選択に戻る
				Client.purchase(user);
				System.exit(0);
			}

			conn.setAutoCommit(false); // オートコミットはオフ
			// UPDATE文の準備
			String sql2 = "UPDATE STOCK SET QUANTITY = ? WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			pStmt2.setInt(1, updQuantity);
			pStmt2.setInt(2, branch);
			pStmt2.setString(3, color);
			pStmt2.setString(4, size);

			// UPDATE文を実行する
			pStmt2.executeUpdate();

			// コミットする
			conn.commit();

			pStmt.close();
			pStmt2.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 指定された支店の在庫を取得
	public ArrayList<StockEntity> chkStock(int branch_id) {

		ArrayList<StockEntity> list = new ArrayList<StockEntity>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SQL文の準備
			String sql = "SELECT * FROM STOCK WHERE BRANCH_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			// 結果表から在庫情報を取得し、表示する
			while (rs.next()) {
				StockEntity entity = new StockEntity();
				entity.setBranchCode(rs.getInt("branch_id")); // 支店コード
				entity.setBranchName(rs.getString("branch_name")); // 支店名
				entity.setProductCode(rs.getInt("product_code")); // 商品コード
				entity.setProductName(rs.getString("product_name")); // 商品名
				entity.setColor(rs.getString("color")); // カラー
				entity.setSize(rs.getString("size")); // サイズ
				entity.setPrice(rs.getInt("price")); // 価格
				entity.setNumber(rs.getInt("quantity")); // 数量

				list.add(entity);
			}
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// 発注後の在庫更新処理
	public void orderUpdStock(int branch_id, int orderQuantity, String color, String size, int price) {

		try (Connection conn = DriverManager.getConnection(Constant.url, Constant.user, Constant.password)) {

			// SQL文の準備
			String sql = "SELECT * FROM STOCK WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);
			pStmt.setString(2, color);
			pStmt.setString(3, size);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			// 結果表から在庫数を取得
			rs.next();
			int quantity = rs.getInt("quantity");
			int totalquantity = orderQuantity + quantity;

			// UPDATE文の準備
			String updSql = "UPDATE STOCK SET QUANTITY = ?, PRICE = ? WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";

			// オートコミットはオフ
			// conn.setAutoCommit(false);

			PreparedStatement pStmt2 = conn.prepareStatement(updSql);
			pStmt2.setInt(1, totalquantity);
			pStmt2.setInt(2, price);
			pStmt2.setInt(3, branch_id);
			pStmt2.setString(4, color);
			pStmt2.setString(5, size);

			// UPDATE文を実行
			pStmt2.executeUpdate();

			pStmt.close();
			pStmt2.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
