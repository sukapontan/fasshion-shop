package entity;

public class EarningsEntity {

	private int number; // No
	private int earnings; // 売上金額
	private String ins_date; // 売上計上日
	private int branch_id; // 支店コード
	private int del_flg; // 削除フラグ
	private String product_name; // 商品名
	private int quantity; // 数量
	private String color; // カラー
	private String size; // サイズ

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEarnings() {
		return earnings;
	}

	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

}
