public class ArticuloMain {

	public enum Option {Salir, Nuevo, Editar, Eliminar, Listar};
	static boolean repeat = true;
	
	public static void main(String[] args) {
		
		App.connect();
		
		do {
			switch (ArticuloUI.scan(Option.class)) {
			case Salir:
				repeat = false;
				System.out.println("\n\nAdios.");
				break;
				
			case Nuevo:
				ArticuloDao.nuevo(ArticuloUI.nuevoArticulo());;
				break;

			case Editar:
				ArticuloDao.editar(ArticuloUI.editarArticulo(ArticuloUI.seleccionarArticulo()));
				break;
				
			case Eliminar:
				ArticuloDao.eliminar(ArticuloUI.seleccionarArticulo());
				break;
				
			case Listar:
				ArticuloUI.listarArticulos();
				break;
				
			default:
				break;
			}
		}while(repeat);
		
		App.disconnect();
		
	}
}
