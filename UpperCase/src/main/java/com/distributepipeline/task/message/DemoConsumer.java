package com.distributepipeline.task.message;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.distributepipeline.task.model.Input;
import com.distributepipeline.task.model.Output;
import com.distributepipeline.task.model.ReportModel;
@Service
public class DemoConsumer {
    
    private CountDownLatch latch = new CountDownLatch(1);
      public CountDownLatch getLatch() {
        return latch;
      }
    
      @Autowired
      Output output;
     
     @Autowired
     DemoProducer demoProducer;
     
     @Autowired
     ReportModel reportModel;
    
     private String jobid;
     private String taskname;
    public String getJobid() {
        return jobid;
    }
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    public String getTaskname() {
        return taskname;
    }
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
    
    
    @KafkaListener(topics = "upperCase", 
              containerFactory = "reportKafkaListenerContainerFactory")
            public void inputlistener(Input  inputdata) throws IOException, InterruptedException {
          System.out.println(inputdata.getInput());
                        
            String url= inputdata.getInput()[0];
            reportModel.setTaskStartTime(new Date(System.currentTimeMillis()));
            reportModel.setJobEndTime(null);
            System.out.println("start time:"+ reportModel.getTaskStartTime());
            reportModel.setTaskAlias(inputdata.getTaskname());
            reportModel.setJobId(inputdata.getJobId());
            demoProducer.sendReport(reportModel);
               url= url.toUpperCase(); 
               
               String[] str= {url};
               
               output.setOutput(str);
                output.setErrcode(200);
                output.setStderr("task complete");
                output.setJobId(inputdata.getJobId());
                output.setTaskName(inputdata.getTaskname());
                output.setUserName(inputdata.getUserName());
                output.setType(inputdata.getType());
               demoProducer.sendMessage(output);
                reportModel.setTaskEndTime(new Date(System.currentTimeMillis()));
                System.out.println("End time:"+ reportModel.getTaskEndTime());
               
                reportModel.setTaskLogs("[INFO]:"+output.getStderr());
                
                demoProducer.sendReport(reportModel);
                
    }
}