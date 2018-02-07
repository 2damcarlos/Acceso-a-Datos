package serpis.ad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VentaMain {
	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("serpis.ad.gventa");
		
		//newArticulo();
		//showAll();
		showAll(Cliente.class);
		showAll(Categoria.class);
		showAll(Articulo.class);
		
		entityManagerFactory.close();
	}

//	private static void showAll() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		List<Cliente>  clientes = entityManager
//				.createQuery("from Cliente order by id", Cliente.class)
//				.getResultList();
//		for (Cliente cliente : clientes)
//			System.out.println(cliente);
//		entityManager.getTransaction().commit();
//	}
	
	private static <TEntity> void showAll(Class<TEntity> entityType) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String queryString = String.format("from %s order by id", entityType.getSimpleName());
		List<TEntity>  entities = entityManager
				.createQuery(queryString, entityType)
				.getResultList();
		for (TEntity entity : entities)
			System.out.println(entity);
		entityManager.getTransaction().commit();
	}

	private static void newArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.getReference(Categoria.class, 1L);
		Articulo articulo = new Articulo();
		articulo.setNombre("nuevo " + new Date());
		articulo.setPrecio(new BigDecimal(6));
		articulo.setCategoria(categoria);
		entityManager.persist(articulo);
		entityManager.getTransaction().commit();
	}
	
	
}
