package Dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		}
		
		try {
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "khushi123");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
		return con;
	}
}
