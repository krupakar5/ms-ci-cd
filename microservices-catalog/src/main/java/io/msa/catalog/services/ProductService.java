package io.msa.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.msa.catalog.models.Product;
import io.msa.catalog.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);

	}

	public List<Product> findByProductId(Long id) {
		return productRepository.findById(id);
	}

	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> fetchByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}

	public void deleteById(Long id) {
		productRepository.delete(id);
	}

}
