package com.promineotech.mechanicApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mechanicApi.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
