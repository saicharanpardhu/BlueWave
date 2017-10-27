//package com.sr.services;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.never;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.runners.MockitoJUnitRunner; 
//import com.sr.domain.EngineModel;
//import com.sr.repository.EngineRepository; 
//
//@RunWith(MockitoJUnitRunner.class)
//public class EngineServiceTests {
//	@Spy
//    private EngineServiceImpl reportServiceSpy;
//    
//    @Mock
//    private EngineRepository reportRepository;
//    
//    @Mock
//    private EngineModel report;
//    
//    @Mock
//    private Iterable<EngineModel> reportList;
//    
//    @Test(expected=NullPointerException.class)
//    public void shouldThrowNullPointerException_whenGetReportByIdIsCalledWithoutContext() throws Exception {
//        //Act
//    	EngineModel retrievedReport = reportServiceSpy.getReportById(new Long(5));
//        //Assert
//        assertThat(retrievedReport, is(equalTo(report)));
//    }
//   
//   @Test(expected=NullPointerException.class)
//   public void shouldThrowNullPointerException_whenSaveReportIsCalledWithoutContext() throws Exception {
//        //Arrange
//        Mockito.doReturn(report).when(reportRepository).save(report);
//        //Act
//        EngineModel savedReport = reportServiceSpy.saveReport(report);
//        //Assert
//        assertThat(savedReport, is(equalTo(report)));
//    } 
//   
//   @Test
//    public void shouldVerifyThatOnlyGetProductByIdIsCalled() throws Exception {
//        //Arrange
//        Mockito.doReturn(report).when(reportServiceSpy).getReportById(new Long(5));
//        //Act
//        EngineModel retrievedReport = reportServiceSpy.getReportById(new Long(5));
//        //Assert
//        Mockito.verify(reportServiceSpy).getReportById(new Long(5));
//        Mockito.verify(reportServiceSpy,never()).saveReport(report);
//        Mockito.verify(reportServiceSpy,never()).getReportByYear(2017);
//        Mockito.verify(reportServiceSpy,never()).getReportByUser("akshaydv");
//    }
//   
//   @Test
//   public void shouldVerifyThatOnlyGetProductByYearIsCalled() throws Exception {
//       //Arrange
//       Mockito.doReturn(reportList).when(reportServiceSpy).getReportByYear(2017);
//       //Act
//       Iterable<EngineModel> retrievedReport = reportServiceSpy.getReportByYear(2017);
//       //Assert
//       Mockito.verify(reportServiceSpy).getReportByYear(2017);
//       Mockito.verify(reportServiceSpy,never()).saveReport(report);
//       Mockito.verify(reportServiceSpy,never()).getReportById(new Long(5));
//       Mockito.verify(reportServiceSpy,never()).getReportByUser("akshaydv");
//   }
//   @Test
//   public void shouldVerifyThatOnlyGetProductByUseridIsCalled() throws Exception {
//       //Arrange
//       Mockito.doReturn(reportList).when(reportServiceSpy).getReportByUser("akshaydv");
//       //Act
//       Iterable<EngineModel> retrievedReport = reportServiceSpy.getReportByUser("akshaydv");
//       //Assert
//       Mockito.verify(reportServiceSpy).getReportByUser("akshaydv");
//       Mockito.verify(reportServiceSpy,never()).saveReport(report);
//       Mockito.verify(reportServiceSpy,never()).getReportByYear(2017);
//       Mockito.verify(reportServiceSpy,never()).getReportById(new Long(5));
//   }
//    @Test
//    public void shouldVerifyThatOnlySaveReportIsNotCalled() throws Exception {
//        //Arrange
//        Mockito.doReturn(report).when(reportServiceSpy).getReportById(new Long(5));
//        //Act
//        EngineModel retrievedReport = reportServiceSpy.getReportById(new Long(5));
//        //Assert
//        Mockito.verify(reportServiceSpy,never()).saveReport(report);
//        Mockito.verify(reportServiceSpy,never()).getReportByUser("akshaydv");
//        Mockito.verify(reportServiceSpy,never()).getReportByYear(2017); 
//    }  
//}