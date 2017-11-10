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




@RunWith(MockitoJUnitRunner.class)
public class PersistenceServiceTest {
	
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
    
    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenSaveWorkflowIsCalledWithoutContext() throws Exception {
         //Arrange
         Mockito.doReturn(workflow).when(persistenceWorkflowRepository).save(workflow);
         //Act
         String savedWorkflow = persistServiceSpy.addWorkflow(workflow);
         //Assert
         assertThat(savedWorkflow, is(equalTo(workflow)));
     } 
    
    
    @Test
    public void shouldVerifyThatOnlyGetWorkflowByNameIsCalled() throws Exception {
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
    
    @Test
    public void shouldVerifyThatOnlyGetWorkflowCalled() throws Exception {
        //Arrange
    	 Mockito.doReturn(workflowList).when(persistServiceSpy).getWorkflow() ;
        //Act
        Iterable<Workflow> retrievedWorkflow= persistServiceSpy.getWorkflow();
        //Assert
        Mockito.verify(persistServiceSpy).getWorkflow();
        Mockito.verify(persistServiceSpy,never()).getWorkflowByName("CAL");
        Mockito.verify(persistServiceSpy,never()).addWorkflow(workflow);

    }
    @Test
    public void shouldVerifyThatOnlyWorkflowUpdateIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(workflow).when(persistServiceSpy).updateWorkflow(workflow);
        //Act
        Workflow workflowReport = persistServiceSpy.updateWorkflow(workflow);
        //Assert
        Mockito.verify(persistServiceSpy).updateWorkflow(workflowReport);
        Mockito.verify(persistServiceSpy,never()).addWorkflow(workflow);
        Mockito.verify(persistServiceSpy,never()).getWorkflow() ;
       
    }
    

    
    
}