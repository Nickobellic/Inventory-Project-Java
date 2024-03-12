package model;

public class Product {
	private int product_id;
	private String product_name;
	private int sell_quantity;
	private int product_price;
	private int product_quantity;
	
	public int getProductID() {
		return product_id;
	}
	
	public String getProductName() {
		return product_name;
	}
	
	public int getSellQuantity() {
		return sell_quantity;
	}
	
	public int getProductQuantity() {
		return product_quantity;
	}
	
	public int getProductPrice() {
		return product_price;
	}
	
	public void setProductID(int id) {
		this.product_id = id;
	}
	
	public void setProductName(String name) {
		this.product_name = name;
	}
	
	public void setSellQuantity(int sell) {
		this.sell_quantity = sell;
	}
	
	public void setProductQuantity(int quant) {
		this.product_quantity = quant;
	}
	
	public void setProductPrice(int product_price) {
		this.product_price = product_price;
	}
	
	
}
