package com.distributedpipeline.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.NotNullException;
import com.distributedpipeline.persistence.exceptions.WorkflowAlreadyExistsException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.repo.PersistenceWorkflowRepo;

@Service
public class PersistenceServiceImpl implements PersistenceService {
	
	/*------------------------ Repositories Autowired ----------------------------------*/
	@Autowired
	private PersistenceWorkflowRepo persistenceWorkflowRepo;
	
		
	/*---------------------- getters and setters for repos-----------------------------*/
     
	public PersistenceWorkflowRepo getPersistenceWorkflowRepo() {
		return persistenceWorkflowRepo;
	}

	public void setPersistenceWorkflowRepo(PersistenceWorkflowRepo persistenceWorkflowRepo) {
		this.persistenceWorkflowRepo = persistenceWorkflowRepo;
	}

	/*------------------------                    ----------------------------------------
                               Method For Workflow    
     --------------------------                    --------------------------------------*/
	
	
	/*----------------------- Method to get workflow by name -----------------------------*/
	@Override
	public Workflow getWorkflowByName(String workFlowName) throws WorkflowNotFoundException {
	       return persistenceWorkflowRepo.getWorkflowByworkFlowName(workFlowName);
       }
		
	
	
	/*------------------------- Method to get all workflows -----------------------------*/
	@Override
	public Iterable<Workflow> getWorkflow() throws WorkflowNotFoundException {
		return persistenceWorkflowRepo.findAll();
	}

	/*--------------------- Method to save workflow to repository -----------------------*/	
	@Override
	public Workflow addWorkflow(Workflow workflow) {
		return persistenceWorkflowRepo.save(workflow);
	}
	
	/*-------------------------- Method to update a workflow ----------------------------*/
	@Override
	public Workflow updateWorkflow(Workflow workFlow) {
		String workflowName = workFlow.getWorkFlowName();
		persistenceWorkflowRepo.deleteByworkFlowName(workflowName);
		return persistenceWorkflowRepo.save(workFlow);
	}

	/*--------------------------- Method to delete workflow ----------------------------*/
	@Override
	public boolean deleteWorkflow(String workflowName) {
		if(persistenceWorkflowRepo.getWorkflowByworkFlowName(workflowName) != null) {
			persistenceWorkflowRepo.deleteByworkFlowName(workflowName);
			return true;
		}
		else {
			return false;
		}
	}
}
