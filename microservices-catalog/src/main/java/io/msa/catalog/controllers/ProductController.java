package io.msa.catalog.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.msa.catalog.models.Product;
import io.msa.catalog.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<Product> fetchById(@PathVariable("id") Long id) {
		return productService.findByProductId(id);

	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Iterable<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping(value = "/findbyProName/{productName}", method = RequestMethod.GET)
	public List<Product> fetchByUserName(@PathVariable String productName) {
		return productService.fetchByProductName(productName);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteByProductId(@PathVariable Long id) {
		productService.deleteById(id);

	}
}
