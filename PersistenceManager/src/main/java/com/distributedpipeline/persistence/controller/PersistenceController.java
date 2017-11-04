package com.distributedpipeline.persistence.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.exceptions.NotNullException;
import com.distributedpipeline.persistence.exceptions.WorkflowAlreadyExistsException;
import com.distributedpipeline.persistence.exceptions.WorkflowNotFoundException;
import com.distributedpipeline.persistence.message.PersistenceProducer;
import com.distributedpipeline.persistence.service.PersistenceService;
import com.distributedpipeline.persistence.service.PersistenceServiceImpl;
import com.distributedpipeline.persistence.utility.LogExecutionTime;

@Controller
@RequestMapping("/v1.0/persistence")
public class PersistenceController {
//	final static Logger logger = Logger.getLogger(PersistenceController.class);
	
	@Autowired
	PersistenceProducer persistenceProducer;
	
	@Autowired
	private PersistenceServiceImpl persistenceservice;
		
	/*----------------------------------Get workflow ------------------------------------ */
	@RequestMapping(value="/workflow" , method=RequestMethod.GET)
	public ResponseEntity<Iterable<Workflow>> getWorkflow() throws WorkflowNotFoundException {
		Iterable<Workflow> workflow = persistenceservice.getWorkflow();
		if(workflow == null)
		{
		   return new ResponseEntity<Iterable<Workflow>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Iterable<Workflow>>(persistenceservice.getWorkflow(),HttpStatus.OK);
	}

   /* ------------------------------ get workflow by name ------------------------------- */
	@RequestMapping(value = "/workflow/{workFlowName}", method = RequestMethod.GET)
	public ResponseEntity<Workflow> getWorkFlow(@PathVariable("workFlowName") String name) throws WorkflowNotFoundException, NotNullException {
		return new ResponseEntity<Workflow>(persistenceservice.getWorkflowByName(name), HttpStatus.OK);
	}
		
	/*---------------------------------add workflow -------------------------------------- */
	@RequestMapping(value="/workflow", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity addWorkflow( @RequestBody  Workflow workflow) {
		try {
			return new ResponseEntity<Workflow>(persistenceservice.addWorkflow(workflow), HttpStatus.OK);
		}
		catch(Exception e) { 
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/*--------------------------------update workflow -------------------------------------*/
	@RequestMapping(value="/workflow", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<String> updateWorkflow(@RequestBody Workflow workflow) throws WorkflowAlreadyExistsException {
		Workflow workflow1= persistenceservice.updateWorkflow(workflow);
        if(workflow1==workflow) {
             return new ResponseEntity<String>("workflow updated", HttpStatus.OK); 
        }
        throw new WorkflowAlreadyExistsException("workflow already exists");
	}

	/*-------------------------------- delete workflow ----------------------------------- */
	@RequestMapping(method=RequestMethod.DELETE, value="/workflow/{name}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable(value="name") String name){
		persistenceservice.deleteWorkflow(name);
		return new ResponseEntity<String>("Deleted succesfully", HttpStatus.OK);
    }
}
