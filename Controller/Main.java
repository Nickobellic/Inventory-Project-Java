package Controller;
import java.sql.SQLException;
import java.util.Scanner;
import dao.ProductDAO;
import model.Login;
import dao.LoginDAO;
import model.Product;

public class Main {
	
	public boolean overallChoice(Scanner sc, Login l, LoginDAO dao, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {		// Function to choose Roles (Admin, Agent)
		System.out.print("\n****************************************************");
		System.out.print("\n1. Admin");
		System.out.print("\n2. Agent");
		System.out.print("\n3. Exit");
		System.out.print("\n****************************************************");
		
		
		System.out.println("\nEnter your choice");
		int overChoice = sc.nextInt();
		switch(overChoice) {
		case 1:
			adminLogin(sc, l, dao, prod, pdao);
			return true;
			
		
		case 2:
			agentLogin(sc, l, dao, prod, pdao);
			return true;
		
		case 3:
			System.out.print("\nProgram exited Successfully.");
			return false;
		
		default:
			System.out.print("Invalid Choice");
			return true;
		}
	}
	
	public boolean adminLogin(Scanner sc, Login l, LoginDAO dao, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {	// Function to authenticate Admin's Login and displays options depending upon Authentication
		System.out.print("\n****************************************************");
		System.out.print("\nAdmin Login");
		System.out.println("\nEnter your username");
		String adminName = sc.next();
		l.setUsername(adminName);
		System.out.println("\nEnter your password");
		String adminPass = sc.next();
		l.setPassword(adminPass);
		l.setRole("Admin");
				
		
		if(dao.loginValidation(l)) {
			System.out.print("\nLogin Success");
			System.out.print("\n****************************************************");
			adminChoice(sc, prod, pdao);
			return true;
		} else {
			System.out.print("\nAdmin unauthorized");
			System.out.print("\n****************************************************");
			return true;
		}

	}
	
	public boolean adminChoice(Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {	// Function that allows Admin to choose the options available to them
		System.out.print("\n****************************************************");
		System.out.print("\n1. Add Product");
		System.out.print("\n2. Display Product");
		System.out.print("\n3. Update Product");
		System.out.print("\n4. Delete Product");
		System.out.println("\n5. Logout");
		System.out.println("****************************************************");
		
		int adminChoiceOption = sc.nextInt();
		switch(adminChoiceOption) {
		case 1:
			addProduct(sc, prod, pdao);
			return true;
		
		case 2:
			displayProduct("admin", sc, prod, pdao);
			return true;
		
		case 3:
			updateProduct(sc,prod, pdao);
			return true;
			
		case 4:
			deleteProduct(sc, prod, pdao);
			return true;
			
		case 5:
			System.out.print("\nAdmin logged out");
			return false;
		
		default:
			System.out.println("Invalid Choice");
			return adminChoice(sc, prod, pdao);
		}
		
	}

	public boolean agentLogin(Scanner sc, Login l, LoginDAO dao, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {	// Function that authenticates Agent's Login and displays options depending upon the authentication
		System.out.print("\nAgent Login");
		System.out.println("\nEnter your username");
		String agentName = sc.next();
		l.setUsername(agentName);
		System.out.println("\nEnter your password");
		String agentPass = sc.next();
		l.setPassword(agentPass);
		
		l.setRole("Agent");
		
		
		if(dao.loginValidation(l)) {
			System.out.print("\nLogin Success");
			agentChoice(sc, prod, pdao);
		} else {
			System.out.print("\nAgent unauthorized");
		}
		
		return true;
	}
	
	public void addProduct(Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {	// Function that performs the Insertion of Product to the Database
		System.out.print("\nAdd Product");
		System.out.println("\nEnter the product ID");
		int productId = sc.nextInt();
		prod.setProductID(productId);
		System.out.println("\nEnter the product Name");
		sc.nextLine();
		String productName = sc.nextLine();
		prod.setProductName(productName);
		System.out.println("\nEnter the minimum sell quantity");
		int productSellQuantity = sc.nextInt();
		prod.setSellQuantity(productSellQuantity);
		System.out.println("\nEnter the price");
		int productPrice = sc.nextInt();
		prod.setProductPrice(productPrice);
		System.out.println("\nEnter the quantity");
		int productQuantity = sc.nextInt();
		prod.setProductQuantity(productQuantity);
		
		pdao.addProduct(prod);
		
		System.out.print("Products added successfully");
		adminChoice(sc, prod, pdao);
	}
	
	public void displayProduct(String role, Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {	// Function that displays the Product that are listed
		pdao.displayProduct();
		if(role.equals("admin")) {
			adminChoice(sc, prod, pdao);
		} else {
			agentChoice(sc, prod, pdao);
		}
	}
	
	public void updateProduct(Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {
		System.out.println("\nEnter the Product ID to update: ");
		int id = sc.nextInt();
		
		System.out.println("1. Product Name\n2.Sell Quantity \n3.Product Price\n4.Product Quantity\nEnter the choice which you want to update : ");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				System.out.println("\nEnter the new Product Name : ");
				sc.nextLine();
				String new_product_name = sc.nextLine();
				pdao.updateProduct(new_product_name, 2, id);
				break;
			
			case 2:
				System.out.println("\nEnter the new Sell Quantity : ");
				int new_sell_quantity = sc.nextInt();
				pdao.updateProduct(Integer.toString(new_sell_quantity), 3, id);
				break;
			
			case 3:
				System.out.println("\nEnter the new Product Price : ");
				int new_product_price = sc.nextInt();
				pdao.updateProduct(Integer.toString(new_product_price), 4, id);
				break;
				
			case 4:
				System.out.println("\nEnter the new Product Quantity : ");
				int new_product_quantity = sc.nextInt();
				pdao.updateProduct(Integer.toString(new_product_quantity), 5, id);
				break;
			
			default:
				System.out.print("\nInvalid Choice");
				break;
		}
		
		adminChoice(sc, prod, pdao);
		

	}
	
	public void deleteProduct(Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {
		System.out.println("\nEnter the Product ID : ");
		int productID = sc.nextInt();
		
		pdao.deleteProduct(productID);
		
		adminChoice(sc, prod, pdao);
	}
	
	public boolean agentChoice(Scanner sc, Product prod, ProductDAO pdao) throws ClassNotFoundException, SQLException {		// Function that presents the Agent's available choices
		System.out.print("\n****************************************************");
		System.out.print("\n1. Display Product");
		System.out.println("\n2. Logout");
		System.out.println("****************************************************");
		
		int agentChoiceOption = sc.nextInt();
		
		switch(agentChoiceOption) {
		case 1:
			displayProduct("agent", sc, prod, pdao);
			return true;
			
		case 2:
			System.out.print("\nAgent logged out");
			return false;
		
		default:
			System.out.print("Invalid Choice");
			return agentChoice(sc, prod, pdao);
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		LoginDAO dao = new LoginDAO();
		ProductDAO pdao = new ProductDAO();
		Login l = new Login();
		Main obj = new Main();
		Product prod = new Product();
		
		
		while(obj.overallChoice(sc, l, dao, prod, pdao)) {	// Loop runs till admin or agent gives exit as option
			obj.overallChoice(sc, l, dao, prod, pdao);
		}

		

}

}
