package com.promineotech.mechanicApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mechanicApi.entity.Job;
import com.promineotech.mechanicApi.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createJob(@RequestBody Job job){
		return new ResponseEntity<Object>(service.createService(job), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getJobs(){
		return new ResponseEntity<Object>(service.getJobs(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getJob(@PathVariable Long id){
		return new ResponseEntity<Object>(service.getJobById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateJob(@RequestBody Job job, @PathVariable Long id){
		try {
			return new ResponseEntity<Object>(service.updateJob(job, id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Could not update job.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteJob(@PathVariable Long id){
		try {
			service.deleteJob(id);
			return new ResponseEntity<Object>("Successfully deleted job with id: "+ id, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Could not delete job.", HttpStatus.BAD_REQUEST);
		}
	}

}
