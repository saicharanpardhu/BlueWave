package com.distributedpipeline.persistence.repo;

import org.springframework.data.repository.CrudRepository;

import com.distributedpipeline.persistence.domain.JobIdDetails;


public interface PersistenceJobRepos extends CrudRepository<JobIdDetails, String> {

	/*-------------- Custom Query for get Job Details by Job ID -----------------------------*/
	public JobIdDetails getJobIdDetailsByJobId(String jobId);
	/*-------------- Custom Query for get Job Details by user name -----------------------------*/
	public JobIdDetails getJobIdDetailsByUserName(String userName);
	/*-------------- Custom Query for get all Job Details by user name -----------------------------*/
	public Iterable<JobIdDetails> getAllJobIdDetailsByUserName(String userName);
	
}
