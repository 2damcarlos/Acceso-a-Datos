package serpis.ad;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//TODO completar mediante hibernate

public class MainClass {
	
	static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hexamen");
		
		Categoria categoria = load(1L); 
		categoria.setNombre(categoria.getNombre() + "#j");
		update(categoria.getId());

	}
	
	public static Categoria load(long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String query = "from Categoria where id = " + id;
		Categoria categoria = entityManager.createQuery(query, Categoria.class).getSingleResult();
		
		entityManager.getTransaction().commit();
		
		return categoria;
	}
	
	public static void update(long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre(categoria.getNombre() + "*");
		entityManager.getTransaction().commit();
	}
}
