package com.promineotech.mechanicApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mechanicApi.entity.Order;
import com.promineotech.mechanicApi.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createOrder(@RequestBody Order order){
		return new ResponseEntity<Object>(service.createOrder(order), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getOrders(){
		return new ResponseEntity<Object>(service.getOrders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getOrderById(@RequestBody Long id) {
		return new ResponseEntity<Object>(service.getOrderById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateOrder(@RequestBody Order order, @PathVariable Long id) throws Exception{
		try {
			return new ResponseEntity<Object>(service.updateOrder(order, id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Unable to update product.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteOrder(@PathVariable Long id) throws Exception{
		try {
			service.deleteOrder(id);
			return new ResponseEntity<Object>("Successfully deleted order with id: " + id, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Unable to delete order.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}/totalInvoice", method=RequestMethod.GET)
	public ResponseEntity<Object> getTotalInvoice(@RequestBody Order order){
		return new ResponseEntity<Object>(service.getTotalInvoice(order), HttpStatus.OK);
	}
	

}
