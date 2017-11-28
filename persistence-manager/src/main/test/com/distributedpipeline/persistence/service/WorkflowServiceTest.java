package com.distributedpipeline.persistence.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.repo.PersistenceWorkflowRepo;
import com.distributedpipeline.persistence.service.PersistenceServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class WorkflowServiceTest {
	
	@Spy
    private PersistenceServiceImpl persistServiceSpy;
    
    @Mock
    private PersistenceWorkflowRepo persistenceWorkflowRepository;
    
    @Mock
    private Workflow workflow;
  
    
    @Mock
    private Iterable<Workflow> workflowList;
    
    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetworkflowBynameIsCalledWithoutContext() throws Exception {
        //Act
    	Workflow retrievedworkflow = persistServiceSpy.getWorkflowByName("CAL");
        //Assert
        assertThat(retrievedworkflow, is(equalTo(workflow)));
    }
    
    /*------------------- Test to verify that only when saveWorkflow without context is called it must
      throw null pointer exception -------------------------------------------------------------------*/
    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenSaveWorkflowIsCalledWithoutContext() throws Exception {
         //Arrange
         Mockito.doReturn(workflow).when(persistenceWorkflowRepository).save(workflow);
         String savedWorkflow = persistServiceSpy.addWorkflow(workflow);
     } 
    
    /*------------------- Test to verify that only getWorkflowByName is called --------------------*/
    @Test
    public void testGetWorkflowByName() throws Exception {
        //Arrange
        Mockito.doReturn(workflow).when(persistServiceSpy).getWorkflowByName("CAL");
        //Act
        Workflow workflowReport = persistServiceSpy.getWorkflowByName("CAL");
        //Assert
        Mockito.verify(persistServiceSpy).getWorkflowByName("CAL");
        Mockito.verify(persistServiceSpy,never()).addWorkflow(workflow);
        Mockito.verify(persistServiceSpy,never()).getWorkflow() ;
        Mockito.verify(persistServiceSpy,never()).updateWorkflow(workflowReport);/*Check this*/
    }
    
    /*------------------- Test to verify that only getWorkflow called ------------------------*/
    @Test
    public void testGetWorkflow() throws Exception {
        //Arrange
    	 Mockito.doReturn(workflowList).when(persistServiceSpy).getWorkflow() ;
        //Act
        Iterable<Workflow> retrievedWorkflow= persistServiceSpy.getWorkflow();
        //Assert
        Mockito.verify(persistServiceSpy).getWorkflow();
        Mockito.verify(persistServiceSpy,never()).getWorkflowByName("CAL");
        Mockito.verify(persistServiceSpy,never()).addWorkflow(workflow);
    }
    
    /*------------------- Test to verify that only workflow update is called --------------------*/
    @Test
    public void testUpdateWorkflow() throws Exception {
        //Arrange
        Mockito.doReturn(workflow).when(persistServiceSpy).updateWorkflow(workflow);
        //Act
        persistServiceSpy.updateWorkflow(workflow);
        //Assert
        Mockito.verify(persistServiceSpy).updateWorkflow(workflow);
        Mockito.verify(persistServiceSpy,never()).addWorkflow(workflow);
        Mockito.verify(persistServiceSpy,never()).getWorkflow() ;
    }
    

    
    
}