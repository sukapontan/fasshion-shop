package entity;

import java.io.Serializable;

public class StockOrderEntity implements Serializable {

	private int number;			// No
	private String product_name;	// 製品名
	private int price;				// 価格（時価）
	private String color;			// カラー
	private String size;			// サイズ
	private int branch_id;			// 支店コード
	private int order_quantity;	// オーダー数
	private int del_flg;			// 削除フラグ
	private int status;			// ステータス
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
