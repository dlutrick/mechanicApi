package com.promineotech.mechanicApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mechanicApi.entity.Vehicle;
import com.promineotech.mechanicApi.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle){
		return new ResponseEntity<Object>(service.createVehicle(vehicle), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> getVehicles(){
		return new ResponseEntity<Object>(service.getVehicles(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getVehicleById(@PathVariable Long id){
		return new ResponseEntity<Object>(service.getVehicleById(id), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> updateVehicle(Vehicle vehicle, Long id){
		return new ResponseEntity<Object>(service.updateVehicle(vehicle, id), HttpStatus.OK);
	}

}
