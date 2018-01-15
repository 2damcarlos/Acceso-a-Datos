package serpis.ad;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebaHibernate {
	
	static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.ghibernate");

		showAll();
		
		modificar(1);

		showAll();
		
		entityManagerFactory.close();
	}
	
	public static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager.createQuery("from Categoria", Categoria.class).getResultList();
		
		for (Categoria categoria : categorias) {
			System.out.println(categoria.toString());
		}
		
		entityManager.getTransaction().commit();
	}
	
	public static void modificar(long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre("modificado" + new Date());
		entityManager.getTransaction().commit();
	}

}
