package dao;
import java.sql.*;
import model.Product;
import ConnectionManager.ConnectionManager;

public class ProductDAO {
	public void addProduct(Product prod) throws SQLException, ClassNotFoundException {
		
		int product_id = prod.getProductID();
		String product_name = prod.getProductName();
		int sell_quantity = prod.getSellQuantity();
		int product_price = prod.getProductPrice();
		int product_quantity = prod.getProductQuantity();
		
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		String query = "INSERT INTO products VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, product_id);
		ps.setString(2, product_name);
		ps.setInt(3, sell_quantity);
		ps.setInt(4, product_price);
		ps.setInt(5, product_quantity);
		
		ps.executeUpdate();
		
		conm.closeConnection();
	}
	
	public void displayProduct() throws ClassNotFoundException, SQLException {
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM products;");
				
		while(rs.next()) {
			System.out.println(rs.getInt("product_id") + " | " + rs.getString("product_name") + " | " + rs.getInt("sell_quantity") + " | " + rs.getInt("product_price") + " | " + rs.getInt("product_quantity"));
		}
		
		conm.closeConnection();
	}
	
	public void updateProduct(String value, int col, int prod_id) throws SQLException, ClassNotFoundException {
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		String query;
		PreparedStatement ps;
		
		switch(col) {
		case 2:
			query = "UPDATE products SET product_name=? WHERE product_id=?";
			ps = con.prepareStatement(query);
			ps.setString(1, value);
			ps.setInt(2, prod_id);
			ps.executeUpdate();
			
			System.out.print("\nProduct Name updated Successfully");
			break;
		
		case 3:
			query = "UPDATE products SET sell_quantity=? WHERE product_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(value));
			ps.setInt(2, prod_id);
			ps.executeUpdate();
			
			System.out.print("\nSell Quantity updated Successfully");
			break;
			
		case 4:
			query = "UPDATE products SET product_price=? WHERE product_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(value));
			ps.setInt(2, prod_id);
			ps.executeUpdate();
			
			System.out.print("\nProduct Price updated Successfully");
			break;
		
		case 5:
			query = "UPDATE products SET product_quantity=? WHERE product_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(value));
			ps.setInt(2, prod_id);
			ps.executeUpdate();
			
			System.out.print("\nProduct Quantity updated Successfully");
			break;
		
		default:
			System.out.print("\nInvalid Choice");
			break;
		}
		
		
	}
	
}
