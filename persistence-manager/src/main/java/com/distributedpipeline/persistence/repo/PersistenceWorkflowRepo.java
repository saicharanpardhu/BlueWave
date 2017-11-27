package com.distributedpipeline.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.distributedpipeline.persistence.domain.Workflow;

@Repository
public interface PersistenceWorkflowRepo extends CrudRepository<Workflow, String> {
	
	/*-------------- Custom Query for get workflow by name -----------------------------*/
	public Workflow getWorkflowByworkFlowName(String workFlowName);
	
}