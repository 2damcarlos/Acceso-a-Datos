package serpis.ad;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class PruebaMySql {

	public static void main(String[] args) {
		System.out.println("Test desde MySql");
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
			
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * from categoria");

			System.out.println("id\tnombre");
			
			while(res.next()) {
				
				int numColumns = res.getMetaData().getColumnCount();
		        for ( int i = 1 ; i <= numColumns ; i++ ) {
		           
		           System.out.print( res.getObject(i) + "\t");
		           
		        }
		        
		        System.out.println();
				
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
