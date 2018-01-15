public class VentaMain {
	
	public enum Option {Salir, Nuevo, Editar, Eliminar, Listar, Vender};
	static boolean repeat = true;
	
public static void main(String[] args) {
		
		App.connect();
		
		do {
			switch (VentaUI.scan(Option.class)) {
			case Salir:
				repeat = false;
				System.out.println("\n\nAdios.");
				break;
				
			case Nuevo:
				VentaDAO.nuevo(VentaUI.nuevoArticulo());;
				break;

			case Editar:
				VentaDAO.editar(VentaUI.editarArticulo(VentaUI.seleccionarArticulo()));
				break;
				
			case Eliminar:
				VentaDAO.eliminar(VentaUI.seleccionarArticulo());
				break;
				
			case Listar:
				VentaUI.listarArticulos();
				break;
				
			case Vender:
				
				break;
				
			default:
				break;
			}
		}while(repeat);
		
		App.disconnect();
		
	}

}
