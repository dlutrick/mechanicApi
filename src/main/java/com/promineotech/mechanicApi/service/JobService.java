package com.promineotech.mechanicApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.Job;
import com.promineotech.mechanicApi.repository.JobRepository;

@Service
public class JobService {
	
	private static final Logger logger = LogManager.getLogger(JobService.class);
	
	@Autowired
	private JobRepository repo;
	
	public Job createService(Job job) {
		return repo.save(job);
	}
	
	public Iterable<Job> getJobs() {
		return repo.findAll();
	}
	
	public Job getJobById(Long id) {
		return repo.findOne(id);
	}
	
	public Job updateJob(Job job, Long id) throws Exception {
		try {
			Job oldJob = repo.findOne(id);
			oldJob.setName(job.getName());
			oldJob.setDescription(job.getDescription());
			oldJob.setPrice(job.getPrice());
			return repo.save(oldJob);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to update job: " + id, e);
			throw new Exception("Unable to update job.");
		}
	}
	
	public void deleteJob(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to delete job: " + id, e);
			throw new Exception("Unable to delete job.");
		}
	}
}
