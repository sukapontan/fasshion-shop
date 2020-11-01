package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.StockOrderEntity;

public class StockOrderDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/fashionshop_suka";
	private final String DB_USER = "root";
	private final String DB_PASS = "Sukapontan0303";

	// 商品発注に関する処理
	public int productOrder(String color, String size, int price, int branch_id, int orderQuantity) {

		// 実行結果件数
		int result = 0;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO STOCKORDER (PRODUCT_NAME,COLOR,SIZE,PRICE,BRANCH_ID,ORDER_QUANTITY,DEL_FLG,STATUS) VALUES('スカＴシャツ',?,?,?,?,?,0,10)";

			// オートコミットはオフ
			// conn.setAutoCommit(false);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, color);
			pStmt.setString(2, size);
			pStmt.setInt(3, price);
			pStmt.setInt(4, branch_id);
			pStmt.setInt(5, orderQuantity);

			// INSERT文を実行
			result = pStmt.executeUpdate();

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	// 発注履歴の表示に関する処理
	public void productOrderCheck(int branch_id) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM STOCKORDER WHERE BRANCH_ID = ? AND STATUS = 10";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);

			// SELECT文を実行
			pStmt.executeQuery();

			ResultSet rs = pStmt.executeQuery();

			// 結果表から発注履歴情報を取得
			while (rs.next()) {

				//発注情報を表示する処理を記述
				String product_name = rs.getString("product_name");
				int price = rs.getInt("price");
				String color = rs.getString("color");
				String size = rs.getString("size");
				int branch = rs.getInt("branch_id");
				int order_quantity = rs.getInt("order_quantity");

				System.out.print(" 商品名：" + product_name);
				System.out.print(" 価格：" + price);
				System.out.print(" カラー：" + color);
				System.out.print(" サイズ：" + size);
				System.out.print(" 支店コード：" + branch);
				System.out.print(" 数量：" + order_quantity + "\n");

			}

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 発注承認に関する処理（発注数を取得する処理）
	public ArrayList<StockOrderEntity> getQuantity(int branch_id) {

		ArrayList<StockOrderEntity> list = new ArrayList<StockOrderEntity>();
		//int updQuantity = 0;
		//int quantity = 0;
		StockOrderEntity entity = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM STOCKORDER WHERE BRANCH_ID = ? AND STATUS = 10";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);

			// SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				// StockOrderEntitiyに取得した値をセット
				entity = new StockOrderEntity();
				entity.setNumber(rs.getInt("no"));
				entity.setProduct_name(rs.getString("product_name"));
				entity.setPrice(rs.getInt("price"));
				entity.setColor(rs.getString("color"));
				entity.setSize(rs.getString("size"));
				entity.setBranch_id(rs.getInt("branch_id"));
				entity.setOrder_quantity(rs.getInt("order_quantity"));
				entity.setDel_flg(rs.getInt("del_flg"));
				entity.setStatus(rs.getInt("status"));
				list.add(entity);

				//quantity = rs.getInt("order_quantity");
				//updQuantity += quantity;
			}

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 発注承認に関する処理(ステータス更新)
	public void orderApproval(int branch_id,int order_quantity,String color,String size) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// UPDATE文の準備
			String sql = "UPDATE STOCKORDER SET STATUS = 20 WHERE BRANCH_ID = ? AND COLOR = ? AND SIZE = ?";

			// オートコミットはオフ
			// conn.setAutoCommit(false);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, branch_id);
			pStmt.setString(2, color);
			pStmt.setString(3, size);

			// UPDATE文を実行
			pStmt.executeUpdate();

			pStmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
