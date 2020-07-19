package com.promineotech.mechanicApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mechanicApi.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
