package org.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.casadocodigo.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("select distinct(p) from " + "Product p join fetch p.prices", Product.class)
				.getResultList();
	}

	public Product find(Long id) {
		try {
			TypedQuery<Product> query = manager
					.createQuery("select distinct(p) from Product p join fetch p.prices where p.id=:id", Product.class)
					.setParameter("id", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
