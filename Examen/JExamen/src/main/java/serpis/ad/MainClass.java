package serpis.ad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainClass {
	
	static Connection connection;

	public static void main(String[] args) {
		
		App.connect();
		
		Categoria categoria = load(1L); 
		categoria.setNombre(categoria.getNombre() + "#j");
		update(categoria);
		
		App.disconnect();
		
	}
	
	private static Categoria load(Object id) {
		
		connection = App.getConnection();
		Categoria categoria  = new Categoria();
		
		PreparedStatement stmt = null;
	    String query = "select * from categoria where id = ?";
	    
	    try {
	    	stmt = connection.prepareStatement(query);
	    	long cat_id = (long) id;
	    	stmt.setLong(1, cat_id);
	    } catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    try {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int id_cat = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            categoria.setId(id_cat);
	            categoria.setNombre(nombre);
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    }
		
		return categoria;
	}
	
	private static void update(Categoria categoria) {
		connection = App.getConnection();
		PreparedStatement stmt = null;
	    String query = "UPDATE Categoria SET nombre = ? where id = ?";
	    
	    try {
	    	stmt = connection.prepareStatement(query);
	    	stmt.setString(1, categoria.getNombre());
	    	stmt.setLong(2, categoria.getId());
	        stmt.execute();
	    } catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    System.out.println("DONE\n");
	}

}
