export class AppConfig {
  public readonly userSignup = "http://172.23.238.176:8088/v0.1/userinfo/user"; 
  public readonly login = "http://172.23.238.176:8087";
  public readonly triggerEngine = "http://172.23.238.186:8021/v1.0/workflowname/";
  public readonly persistenceUrl = "http://172.23.238.151:8080/v1.0/persistence/";
  public readonly saveWorkflow = this.persistenceUrl + "workflow/";
  public readonly removeWorkflow = this.persistenceUrl + "workflow/remove/";
  public readonly reportGetJobId = this.persistenceUrl + "jobdetails/userName/latest/";
  public readonly getReport = "http://172.23.238.176:8080/checkout/";
  public readonly persistence = this.persistenceUrl + "workflow/";
  public readonly socket = "http://172.23.238.216:9000/gs-guide-websocket";
  public readonly getAllWorkflows = this.persistenceUrl + "workflow/users/";
  public readonly getWorkflow = this.persistenceUrl + "workflows/workflow/";
  public readonly getTasksOfWorkflow = this.persistenceUrl + "tasks/";
  public readonly getTaskNumber = this.persistenceUrl + "count/jobdetails/";
}