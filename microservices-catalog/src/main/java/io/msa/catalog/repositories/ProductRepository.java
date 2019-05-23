package io.msa.catalog.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.msa.catalog.models.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

	public List<Product> findById(Long id);
	
	public List<Product> findByProductName(String productName);

}
