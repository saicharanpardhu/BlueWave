package com.distributedpipeline.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.TaskLibraryNotFoundException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.repo.PersistenceTaskRepo;
import com.distributedpipeline.persistence.repo.PersistenceWorkflowRepo;

@Service
public class PersistenceServiceImpl implements PersistenceService {
	
	/*------------------------ Repositories Autowired ----------------------------------*/
	@Autowired
	private PersistenceWorkflowRepo persistenceWorkflowRepo;
	
	@Autowired
	private PersistenceTaskRepo persistenceTaskRepo;
	
	
	/*---------------------- getters and setters for repos-----------------------------*/
     
	public PersistenceWorkflowRepo getPersistenceWorkflowRepo() {
		return persistenceWorkflowRepo;
	}

	public void setPersistenceWorkflowRepo(PersistenceWorkflowRepo persistenceWorkflowRepo) {
		this.persistenceWorkflowRepo = persistenceWorkflowRepo;
	}

	public PersistenceTaskRepo getPersistenceTaskRepo() {
		return persistenceTaskRepo;
	}

	public void setPersistenceTaskRepo(PersistenceTaskRepo persistenceTaskRepo) {
		this.persistenceTaskRepo = persistenceTaskRepo;
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
	
    /*------------------------                    ----------------------------------------
	                            Method For TaskLibrary    
	--------------------------                    --------------------------------------*/
	
	
	/*--------------------- Method to get tasklibrary by taskname------------------------*/
    @Override
	public TaskLibrary gettaskLibraryByName(String taskName) throws TaskLibraryNotFoundException{
		return persistenceTaskRepo.getTaskLibraryByTaskName(taskName);
	} 
	
	/*----------------------- Method to get all tasklibraries ---------------------------*/
	@Override
	public Iterable<TaskLibrary> getTaskLibrary() throws TaskLibraryNotFoundException {
		return persistenceTaskRepo.findAll();
	}
	
	/*--------------------------- Method to save a tasklibrary -------------------------*/
	@Override
	public TaskLibrary addTaskLibrary(TaskLibrary taskLibrary) {
		return persistenceTaskRepo.save(taskLibrary);
	}
	
	/*-------------------------- Method to update a tasklibrary ------------------------*/
	@Override
	public TaskLibrary updateTaskLibrary(TaskLibrary taskLibrary) {
        String taskName = taskLibrary.getTaskName();
		persistenceTaskRepo.deleteByTaskName(taskName);
		return persistenceTaskRepo.save(taskLibrary);
		
	}
	
	/*-------------------------- Method to delete tasklibrary by id -------------------*/
	@Override
	public boolean deleteTaskLibrary(String taskName) {
		if(persistenceTaskRepo.getTaskLibraryByTaskName(taskName) != null)  {
			persistenceTaskRepo.deleteByTaskName(taskName);
			return true;
		}
		else {
			return false;
		}
	}   
}
