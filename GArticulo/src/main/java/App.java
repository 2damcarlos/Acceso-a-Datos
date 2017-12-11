import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	
	static Connection connection;
	
	public static void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
