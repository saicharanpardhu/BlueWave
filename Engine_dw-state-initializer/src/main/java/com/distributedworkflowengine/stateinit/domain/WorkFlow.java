package com.distributedworkflowengine.stateinit.domain;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class WorkFlow {
    private String owner;
    private String[] canViewUser;
    private String[] canEditUser;
    private String workFlowName;
    private String workFlowStatus;
    private Map<String,Task> tasks;
    
    
    public WorkFlow(String owner, String[] canViewUser, String[] canEditUser, String workFlowName,
            String workFlowStatus, Map<String, Task> tasks) {
        super();
        this.owner = owner;
        this.canViewUser = canViewUser;
        this.canEditUser = canEditUser;
        this.workFlowName = workFlowName;
        this.workFlowStatus = workFlowStatus;
        this.tasks = tasks;
    }
    
    public WorkFlow() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String[] getCanViewUser() {
        return canViewUser;
    }
    public void setCanViewUser(String[] canViewUser) {
        this.canViewUser = canViewUser;
    }
    public String[] getCanEditUser() {
        return canEditUser;
    }
    public void setCanEditUser(String[] canEditUser) {
        this.canEditUser = canEditUser;
    }
    public String getWorkFlowName() {
        return workFlowName;
    }
    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }
    public String getWorkFlowStatus() {
        return workFlowStatus;
    }
    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }
    public Map<String, Task> getTasks() {
        return tasks;
    }
    public void setTasks(Map<String, Task> tasks) {
        this.tasks = tasks;
    }
}