import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticuloDao {
	
	static Connection connection;
	
	public static void nuevo(Articulo articulo) {
		connection = App.getConnection();
		PreparedStatement stmt = null;
	    String query = "INSERT INTO Articulo (nombre, precio, categoria) VALUES (?, ?, ?)";
	    
	    try {
	    	stmt = connection.prepareStatement(query);
	    	stmt.setFloat(2, articulo.getPrecio());
	    	stmt.setString(1, articulo.getNombre());
	    	stmt.setInt(3, articulo.getCategoria());
	        stmt.execute();
	    } catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    System.out.println("DONE\n");
	}

	public static void editar(Articulo articulo) {
		connection = App.getConnection();
		PreparedStatement stmt = null;
	    String query = "UPDATE Articulo SET nombre = ?, precio = ?, categoria = ? where id = ?";
	    
	    try {
	    	stmt = connection.prepareStatement(query);
	    	stmt.setFloat(2, articulo.getPrecio());
	    	stmt.setString(1, articulo.getNombre());
	    	stmt.setInt(3, articulo.getCategoria());
	    	stmt.setInt(4, articulo.getId());
	        stmt.execute();
	    } catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    System.out.println("DONE\n");
		
	}

	public static void eliminar(Articulo articulo) {
		connection = App.getConnection();
		PreparedStatement stmt = null;
	    String query = "DELETE FROM articulo where id = ?";
	    
	    try {
	    	stmt = connection.prepareStatement(query);
	    	stmt.setInt(1, articulo.getId());
	        stmt.execute();
	    } catch(SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    System.out.println("DONE\n");
		
	}
	
	public static ArrayList<Categoria> getCategorias() {

		connection = App.getConnection();
		
		ArrayList<Categoria> categorias = new ArrayList<>();
		
		Statement stmt = null;
	    String query = "select * from categoria";
	    
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            Categoria categoria = new Categoria();
	            categoria.setId(id);
	            categoria.setNombre(nombre);
	            
	            categorias.add(categoria);
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    }
		
		return categorias;
		
		
	}
	
	public static ArrayList<Articulo> getArticulos() {

		connection = App.getConnection();
		
		ArrayList<Articulo> articulos = new ArrayList<>();
		
		Statement stmt = null;
	    String query = "select * from articulo";
	    
	    try {
	        stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            
	        	Articulo articulo = new Articulo();
	        	
	        	articulo.setId(rs.getInt("id"));
	        	articulo.setNombre(rs.getString("nombre"));
	            articulo.setPrecio(rs.getFloat("precio"));
	            articulo.setCategoria(rs.getInt("categoria"));
	            
	            articulos.add(articulo);
	        }
	    } catch (SQLException e ) {
	        System.out.println(e.getMessage());
	    }
		
		return articulos;
		
		
	}

}
