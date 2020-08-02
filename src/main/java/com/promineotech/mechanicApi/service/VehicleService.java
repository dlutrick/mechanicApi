package com.promineotech.mechanicApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.Vehicle;
import com.promineotech.mechanicApi.repository.VehicleRepository;

@Service
public class VehicleService {
	
	private static final Logger logger = LogManager.getLogger(VehicleService.class);
	
	@Autowired
	private VehicleRepository repo;
	
	public Vehicle createVehicle(Vehicle vehicle) {
		return repo.save(vehicle);
	}
	
	public Iterable<Vehicle> getVehicles(){
		return repo.findAll();
	}
	
	public Vehicle getVehicleById(Long id) {
		return repo.findOne(id);
	}
	
	public Vehicle updateVehicle(Vehicle vehicle, Long id) throws Exception {
		try {
			Vehicle oldVehicle = repo.findOne(id);
			oldVehicle.setMake(vehicle.getMake());
			oldVehicle.setModel(vehicle.getModel());
			oldVehicle.setYear(vehicle.getYear());
			oldVehicle.setCurrentMiles(vehicle.getCurrentMiles());
			oldVehicle.setLicensePlate(vehicle.getLicensePlate());
			return repo.save(oldVehicle);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to update vehicle: " + id, e);
			throw new Exception("Unable to update vehicle.");
		}
	}
	
	public void deleteVehicle(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to delete vehicle: " + id, e);
			throw new Exception("Unable to delete vehicle.");
		}
	}

}
