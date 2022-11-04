package registrationform;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;

public class Database {

	public static Connection getConnection() {
		Connection con =null;
		try {
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}   

}

