package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		
		try {
			// load driver class
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
			
			//get hold of the DriverManager
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus", "root", "admin");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Where is the MySQL JDBC Driver");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Connection failed check the output console");
			e.printStackTrace();
		}
		
		if (connection != null) {
			System.out.println("Connection made to DB");
		}
		return connection;
	}

}
