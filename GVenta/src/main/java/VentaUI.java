import java.util.ArrayList;
import java.util.Scanner;

public class VentaUI {
	
private static Scanner scanner = new Scanner(System.in);
	
	public static <T extends Enum<T>> T scan(Class<T> enumType){
		
		final T[] constants = enumType.getEnumConstants();
		
		for(int index = 0; index<constants.length; index++) {
			System.out.printf("%s - %s\n", index, constants[index]);
		}
		
		while(true) {
			System.out.print("Elige opcion: ");
			int select = Integer.parseInt(scanner.nextLine());
			if (select>=0 && select<constants.length) {
				return constants[select];
			} else {
				System.out.println("Opcion invalida");
			}
		}
	}
	
public static Articulo nuevoArticulo() {
		
		Articulo articulo = new Articulo();
		
		System.out.print("Nombre del articulo: ");
		articulo.setNombre(scanner.nextLine());
		System.out.print("Precio: ");
		articulo.setPrecio(Float.parseFloat(scanner.nextLine()));
		System.out.print("Elegir la categoría a la que pertenece la categoría\n\n0. Sin categoría\n1. Mostrar lista de categorías\nOpcion: ");
		if(Integer.parseInt(scanner.nextLine())!=0) {
			
			listarCategoría();
			
			System.out.print("Categoría: ");
			
			articulo.setCategoria(Integer.parseInt(scanner.nextLine()));
			
		}
		
		return articulo;
	}
	
	public static void listarCategoría() {
		ArrayList<Categoria> categorias = VentaDAO.getCategorias();
		
		System.out.println("\n\n");
		for(int i = 0;i<categorias.size();i++) {
			System.out.println(categorias.get(i).getId() + " - " + categorias.get(i).getNombre());
		}
	}

	public static Articulo seleccionarArticulo() {

		ArrayList<Articulo> articulos = VentaDAO.getArticulos();
		
		System.out.println("\n\nArticulos:");
		
		for(int i = 0;i<articulos.size();i++) {
			System.out.println((i+1) + " - " + articulos.get(i).getNombre() + " - " + articulos.get(i).getPrecio());
		}
		
		System.out.print("Articulo a selecionar: ");
		
		return articulos.get(Integer.parseInt(scanner.nextLine())-1);
	}
	
	public static void listarArticulos() {

		ArrayList<Articulo> articulos = VentaDAO.getArticulos();
		
		System.out.println("\n\nArticulos:");
		
		System.out.println("ID \t NOMBRE \t PRECIO");
		
		for(int i = 0;i<articulos.size();i++) {
			System.out.println(articulos.get(i).getId() + " \t " + articulos.get(i).getNombre() + " \t " + articulos.get(i).getPrecio());
		}
		
		System.out.println("\n\n");
		
	}

	public static Articulo editarArticulo(Articulo articulo) {
		
		System.out.print("Nombre nuevo: ");
		articulo.setNombre(scanner.nextLine());
		System.out.print("Precio nuevo: ");
		articulo.setPrecio(Float.parseFloat(scanner.nextLine()));
		listarCategoría();
		System.out.print("Categoría nueva: ");
		articulo.setCategoria(Integer.parseInt(scanner.nextLine()));
		
		return articulo;
	}

}
