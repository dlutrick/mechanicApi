package com.promineotech.mechanicApi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.Job;
import com.promineotech.mechanicApi.entity.Order;
import com.promineotech.mechanicApi.entity.Product;
import com.promineotech.mechanicApi.repository.OrderRepository;

@Service
public class OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository repo;
	
	public Order createOrder(Order order) {
		return repo.save(order);
	}
	
	public Iterable<Order> getOrders() {
		return repo.findAll();
	}
	
	public Order getOrderById(Long id) {
		return repo.findOne(id);
	}
	
	public Order updateOrder(Order order, Long id) throws Exception {
		try {
			Order oldOrder = repo.findOne(id);
			oldOrder.setDatePurchased(order.getDatePurchased());
			oldOrder.setTotalPrice(order.getTotalPrice());
			return repo.save(oldOrder);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to update order: " + id, e);
			throw new Exception("Unable to update order.");
		}
	}
	
	public void deleteOrder(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to delete order: " + id, e);
			throw new Exception("Unable to delete order.");
		}
	}
	
	public double totalProductPrice(List<Product> products) {
		double total = 0;
		for(Product product : products) {
			total += product.getPrice();
		}
		return total;
	}
	
	public double totalServicePrice(List<Job> services) {
		double total = 0;
		for(Job service : services) {
			total += service.getPrice();
		}
		return total;
	}
	
	public double getTotalInvoice(Order order) {
		OrderService service = new OrderService();
		double total = 0;
		total += service.totalProductPrice(order.getProducts());
		total += service.totalServicePrice(order.getServices());
		return total;
	}

}
