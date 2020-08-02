package com.promineotech.mechanicApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.Product;
import com.promineotech.mechanicApi.repository.ProductRepository;

@Service
public class ProductService {
	
	private static final Logger logger = LogManager.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository repo;
	
	public Product createProduct(Product product) {
		return repo.save(product);
	}
	
	public Iterable<Product> getProducts(){
		return repo.findAll();
	}
	
	public Product getProduct(Long id) {
		return repo.findOne(id);
	}
	
	public Product updateProduct(Product product, Long id) throws Exception {
		try {
			Product oldProduct = repo.findOne(id);
			oldProduct.setName(product.getName());
			oldProduct.setDescription(product.getDescription());
			oldProduct.setPrice(product.getPrice());
			return repo.save(oldProduct);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to update product: " + id, e);
			throw new Exception("Unable to update product.");
		}
	}
	
	public void deleteProduct(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to delete product: " + id, e);
			throw new Exception("Unable to delete product.");
		}
	}

}
