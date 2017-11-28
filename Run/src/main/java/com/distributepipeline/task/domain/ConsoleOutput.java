package com.distributepipeline.task.domain;

import org.springframework.stereotype.Component;

@Component
public class ConsoleOutput {
    public String getConsole() {
        return console;
    }
    public void setConsole(String console) {
        this.console = console;
    }
    private String userName;
    private String taskName;
    private String console;
    
    
    public ConsoleOutput(String userName, String taskName, String message) {
        super();
        this.userName = userName;
        this.taskName = taskName;
        this.console = console;
    }
    
    
    public ConsoleOutput() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
}
