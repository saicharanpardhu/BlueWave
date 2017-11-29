package com.distributedpipeline.persistence.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.distributedpipeline.persistence.domain.JobIdDetails;
import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.TaskLibraryNotFoundException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.repo.PersistenceJobRepos;
import com.distributedpipeline.persistence.repo.PersistenceTaskRepo;
import com.distributedpipeline.persistence.repo.PersistenceWorkflowRepo;

@Service
public class PersistenceServiceImpl implements PersistenceService {
	
	/*------------------------ Repositories Autowired ----------------------------------*/
	@Autowired
	private PersistenceWorkflowRepo persistenceWorkflowRepo;
	
	@Autowired
	private PersistenceTaskRepo persistenceTaskRepo;
	
	@Autowired
	private PersistenceJobRepos persistenceJobRepos;
	
	@Autowired
	private PersistenceServiceImpl service;
	
	
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
	
	public PersistenceJobRepos getPersistenceJobRepos() {
		return persistenceJobRepos;
	}

	public void setPersistenceJobRepos(PersistenceJobRepos persistenceJobRepos) {
		this.persistenceJobRepos = persistenceJobRepos;
	}
	
	
	
	/*------------------------                    ----------------------------------------
                               Method For Workflow    
     --------------------------                    --------------------------------------*/
	

	/*----------------------- Method to get workflow by name -----------------------------*/
	@Override
	public Workflow getWorkflowByName(String workFlowName) throws WorkflowNotFoundException {
	       return persistenceWorkflowRepo.getWorkflowByworkFlowName(workFlowName);
       }
	
	/*----------------------- Method to get workflow by owner -----------------------------*/
	@Override
	public List<Workflow> getAllWorkflowOfOwner(String userName) {
		Iterable<Workflow> workFlowList = persistenceWorkflowRepo.findAll();
		List<Workflow> listWorkflow = new ArrayList<Workflow>();
		for(Workflow workFlow : workFlowList) {
			if(userName.equals(workFlow.getOwner())) {
				listWorkflow.add(workFlow);
			}
		}
		return listWorkflow;
		
	}	
	
	/*----------------------- Method to get workflow by name and owner -----------------------------*/
	@Override
	public Workflow getWorkflowByNameAndUserName(String workFlowName,String owner) throws WorkflowNotFoundException {
	      
		Iterable<Workflow> workFlowList=persistenceWorkflowRepo.findAll();
		for(Workflow workFlow : workFlowList)
		{
			if(owner.equals(workFlow.getOwner()) && workFlowName.equals(workFlow.getWorkFlowName())) {
				return workFlow;
			}
		}
		return null;	       
       }
	
	
	/*------------------------- Method to get all workflows -----------------------------*/
	@Override
	public Iterable<Workflow> getWorkflow() throws WorkflowNotFoundException {
		return persistenceWorkflowRepo.findAll();
	}

	/*--------------------- Method to save workflow to repository -----------------------*/	
	@Override
	public String addWorkflow(Workflow workflow) {
		int count = 0;
		String workFlowName = workflow.getWorkFlowName();
		String owner = workflow.getOwner();		
		
		Iterable<Workflow> workFlowList=persistenceWorkflowRepo.findAll();
		for(Workflow workFlow : workFlowList)
		{
			if(owner.equals(workFlow.getOwner()) && workFlowName.equals(workFlow.getWorkFlowName())) {
				count++;
			}
		}
		
		if(count ==0) {
			workflow.setTimeStamp(new Timestamp(System.currentTimeMillis()));
			persistenceWorkflowRepo.save(workflow);
			return "Workflow saved";
		}
		else {
			return "Workflow with the same Workflow name already exists";
		}
	}
	
	/*--------------------------- Method to delete workflow ----------------------------*/
	@Override
	public void deleteWorkflow(String workflowName, String owner) throws WorkflowNotFoundException {
	
		Iterable<Workflow> workFlowList=persistenceWorkflowRepo.findAll();
		for(Workflow workFlow : workFlowList)
		{
			if(owner.equals(workFlow.getOwner()) && workflowName.equals(workFlow.getWorkFlowName())) {
				String workflowId = workFlow.getId();
				persistenceWorkflowRepo.delete(workflowId);
			}
		}
	}
	
	/*--------------------------- Method to update workflow ----------------------------*/
	@Override
	public void updateWorkflow(Workflow workflow) throws WorkflowNotFoundException {
		
		String owner = workflow.getOwner();
		String workflowName = workflow.getWorkFlowName();
		Iterable<Workflow> workFlowList=persistenceWorkflowRepo.findAll();
		for(Workflow workFlow : workFlowList)
		{
			if(owner.equals(workFlow.getOwner()) && workflowName.equals(workFlow.getWorkFlowName())) {
				String workflowId = workFlow.getId();
				persistenceWorkflowRepo.delete(workflowId);
				persistenceWorkflowRepo.save(workflow);
			}
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
	
	/*-------------------------- Method to get command of a tasklibrary by task type ------------------------*/
	@Override
	public String getTaskCommand(String taskName) {
        String taskCommand = persistenceTaskRepo.getTaskLibraryByTaskName(taskName).getTaskCommand();
		return taskCommand;
		
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
	
	
//	/*-------------------------- Method to authenticate a user ----------------------------*/
//	@Override
//	public String userPermissions(String workFlowName, String userName) {
//		Workflow workflow = persistenceWorkflowRepo.getWorkflowByworkFlowName(workFlowName);
//		
//		if(workflow!=null && Arrays.toString(workflow.getCanEditUser()).contains(userName)) {
//			return "user can edit workflow";
//		}
//		else if(workflow!=null && Arrays.toString(workflow.getCanExecuteUser()).contains(userName)) {
//			return "user can execute workflow";
//		}
//		else if(workflow!=null && Arrays.toString(workflow.getCanViewUser()).contains(userName)) {
//			return "user can view workflow";
//		}
//		else {
//			return "user not authorised to access workflow";
//		}
//	}
	
	/*-------------------------- Method to get tasks inside workflow -----------------------------*/
	
	@Override
	public List<String> getTasksOfWorkflow(String workFlowName) {
		
		Workflow workflow = persistenceWorkflowRepo.getWorkflowByworkFlowName(workFlowName);
		Map<String,Tasks> tasks = new HashMap<String,Tasks>();
		tasks = workflow.getTasks();
		List<String> task_list = new ArrayList<String>();
		Set<String> keys = tasks.keySet();	
		for (String key : keys) {
			task_list.add(key);
		}
		return task_list;
	}
	
	/*-------------------------- Method to get details of a task in a workflow -----------------------*/
	
	@Override
	public Tasks getDetailsOfTask(String workFlowName, String task_name) {
		
		Workflow workflow = persistenceWorkflowRepo.getWorkflowByworkFlowName(workFlowName);
		Map<String,Tasks> tasks = new HashMap<String,Tasks>();
		Map<String,Tasks> mapOfTasks = new HashMap<String,Tasks>();
		tasks = workflow.getTasks();
		Tasks taskDetails = null;
		for (Map.Entry<String, Tasks> entry : tasks.entrySet()) {	
			if(entry.getKey().equals(task_name)) {
				taskDetails = entry.getValue();
			}
		}
		return taskDetails;
	}
	
	/*------------------------                    ----------------------------------------
	    						Method to fetch Job Details   
	--------------------------                    --------------------------------------*/
	
	
	/*----------------------- Method to get Job details by job id -----------------------------*/
	@Override
	public JobIdDetails getJobDetailsByJobId(String jobId) {
		return persistenceJobRepos.getJobIdDetailsByJobId(jobId);
	}

	/*----------------------- Method to get Job details by user name -----------------------------*/
	@Override
	public Iterable<JobIdDetails> getJobDetailsByUserName(String userName) {
		return persistenceJobRepos.getAllJobIdDetailsByUserName(userName);
	}
		
	/*--------------------- Method to save Job details to repository -----------------------*/	
	@Override
	public String addJobDetails(JobIdDetails jobIdDetails) {
		String jobId = jobIdDetails.getJobId();
		if(persistenceJobRepos.getJobIdDetailsByJobId(jobId)==null) {
			persistenceJobRepos.save(jobIdDetails);
			return "Job details saved";
		}
		else {
			return "Job details with the job id already exists";
		}
	}
	
	/*----------------------- Method to get all Job details -----------------------------*/
	public Iterable<JobIdDetails> getAllJobDetails() {
		return persistenceJobRepos.findAll();
	}
	
	/*----------------------- Method to get Top Ten Job details by user name -----------------------------*/
	public List<JobIdDetails> getTopJobDetails(String userName, int pageNumber, int size) {
		Pageable topTen = new PageRequest(pageNumber, size,Direction.ASC,"jobId");
		return persistenceJobRepos.findLast10ByUserName(userName,topTen);
	}
	
	/*----------------------- Method to get count Job details by user name -----------------------------*/
	public int getCountJobDetails(String userName) {
		int count = 0;
		Iterable<JobIdDetails> listJobDetails = persistenceJobRepos.getAllJobIdDetailsByUserName(userName);
		for(JobIdDetails jobDetails: listJobDetails) {
			count++;
		}
		return count;
		
	}
	
}
