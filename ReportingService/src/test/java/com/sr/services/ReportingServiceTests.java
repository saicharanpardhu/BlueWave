package com.sr.services;

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
import com.sr.domain.Report;
import com.sr.repository.ReportRepository; 

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceTests {
	@Spy
    private ReportingServiceImpl reportServiceSpy;
    
    @Mock
    private ReportRepository reportRepository;
    
    @Mock
    private Report report;
    
    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetReportByIdIsCalledWithoutContext() throws Exception {
        //Act
    	Report retrievedReport = reportServiceSpy.getReportById(new Long(5));
        //Assert
        assertThat(retrievedReport, is(equalTo(report)));
    }
   
   @Test(expected=NullPointerException.class)
   public void shouldThrowNullPointerException_whenSaveReportIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(report).when(reportRepository).save(report);
        //Act
        Report savedReport = reportServiceSpy.saveReport(report);
        //Assert
        assertThat(savedReport, is(equalTo(report)));
    } 
   
   @Test
    public void shouldVerifyThatGetProductByIdIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(report).when(reportServiceSpy).getReportById(new Long(5));
        //Act
        Report retrievedReport = reportServiceSpy.getReportById(new Long(5));
        //Assert
        Mockito.verify(reportServiceSpy).getReportById(new Long(5));
    }
    @Test
    public void shouldVerifyThatSaveReportIsNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(report).when(reportServiceSpy).getReportById(new Long(5));
        //Act
        Report retrievedReport = reportServiceSpy.getReportById(new Long(5));
        //Assert
        Mockito.verify(reportServiceSpy,never()).saveReport(report);
    } 
}