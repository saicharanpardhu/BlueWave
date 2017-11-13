package com.distributedpipeline.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import com.distributedpipeline.persistence.domain.TaskLibrary;


public interface PersistenceTaskRepo extends CrudRepository<TaskLibrary, String> {

	/*-------------- Custom Query for delete task ----------------------------------*/
	public void deleteByTaskName(String taskName);
	/*-------------- Custom Query for get task by name -----------------------------*/
	public TaskLibrary getTaskLibraryByTaskName(String taskName);
	
}
