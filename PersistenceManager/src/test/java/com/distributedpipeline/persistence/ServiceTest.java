package com.distributedpipeline.persistence;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.distributedpipeline.persistence.domain.PersistenceModel;
import com.distributedpipeline.persistence.repo.PersistenceRepo;
import com.distributedpipeline.persistence.service.PersistenceService;
import com.distributedpipeline.persistence.service.PersistenceServiceImpl;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class ServiceTest {
   
     
      private PersistenceServiceImpl persistenceserviceimpl;
      private PersistenceService persistenceservice;
       @Mock
       private PersistenceRepo persistencerepo;
       @Mock
       private PersistenceModel persistencemodel;
       @Before
       public void setupMock() {
           MockitoAnnotations.initMocks(this);
           persistenceserviceimpl=new PersistenceServiceImpl();
           persistenceserviceimpl.setPersistenceRepo(persistencerepo);
       }
       
       @Test
       //Test by id
       public void TestgetById() throws Exception {
           // Arrange
           when(persistencerepo.findOne(2)).thenReturn(persistencemodel);
           // Act
           PersistenceModel retrievedUser = persistenceserviceimpl.getWorkflow(2);
           // Assert
           assertThat(retrievedUser, is(equalTo(persistencemodel)));
      }
       
       @Test
       public void TestUpdate() throws Exception {
           // Arrange
           when(persistencerepo.save(persistencemodel)).thenReturn(persistencemodel);
           // Act
           persistenceserviceimpl.updateWorkflow(persistencemodel);
           // Assert
           assertThat(persistencerepo.findOne(1), is(persistencerepo.findOne(1)));
       }
       @Test
       public void TestDelete() throws Exception {
           // Arrange
           doNothing().when(persistencerepo).delete(1);
           PersistenceRepo my = Mockito.mock(PersistenceRepo.class);
           // Act
           persistenceserviceimpl.deleteWorkflow(1);
           // Assert
           verify(persistencerepo, times(1)).delete(1);
       }
   }