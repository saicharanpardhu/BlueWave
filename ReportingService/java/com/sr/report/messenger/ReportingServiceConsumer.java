package com.sr.report.messenger;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException.SystemErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sr.report.model.ReportModel;
import com.sr.report.repository.*;
 

@Service
public class ReportingServiceConsumer {
	
	 
	  private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		
	    return latch;
	}

	@Autowired
	private ReportModelCRUDRepository reportModelCRUDRepository;
    
    @KafkaListener(topics="reports",
    		containerFactory = "projectModelKafkaListenerContainerFactory")
   public void processMessage(ReportModel report) {
    	
        
        latch.countDown();
        if(report.getJobId()!=null&&report.getTaskAlias()!=null) {
        ReportModel report_db = reportModelCRUDRepository.findByJobIdAndTaskAlias(report.getJobId(),report.getTaskAlias());
        if(report_db!=null) {
        	if(report_db.getJobEndTime() == null)
        	{
        		report_db.setJobEndTime(report.getJobEndTime());
        	}
        	if(report_db.getJobStartTime() == null)
        	{
        		report_db.setJobStartTime(report.getJobStartTime());
        	}
        	if(report_db.getJobStatus() == null)
        	{
        		report_db.setJobStatus(report.getJobStatus());
        	}
        	if(report_db.getTaskEndTime() == null)
        	{
        		report_db.setTaskEndTime(report.getTaskEndTime());
        	}
        	if(report_db.getTaskStartTime() == null)
        	{
        		report_db.setTaskStartTime(report.getTaskStartTime());
        	}
        	if(report_db.getTaskLogs() == null)
        	{
        		report_db.setTaskLogs(report.getTaskLogs());
        	}
        	if(report_db.getWorkFlowName() == null)
        	{
        		report_db.setWorkFlowName(report.getWorkFlowName());
        	}
        	System.out.println("saved report from Db --------------------------------------------------");
        	System.out.println(report_db.getJobId());
        	System.out.println(report_db.getJobStartTime());
        	System.out.println(report.getJobId());
        	System.out.println(report.getJobStartTime());
        	reportModelCRUDRepository.save(report_db);
        }
        else {
        	reportModelCRUDRepository.save(report);
        }
        
        
        
        
        
        
   }
    
   
    }}
