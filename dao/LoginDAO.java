package dao; // DAO => Data Access Object (Used to have MySQL implementations inside Java
import model.Login;
import ConnectionManager.ConnectionManager;
import java.sql.*;

public class LoginDAO {
	public boolean loginValidation(Login l) throws ClassNotFoundException, SQLException{
		
		String username = l.getUsername();
		String password = l.getPass();
		String role = l.getRole();
		
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM login");
		
		while(rs.next()) {
			if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && role.equals(rs.getString("role"))) {
				conm.closeConnection();
				return true;
				
			}
		}
		conm.closeConnection();		
		return false;

		
	}
}
