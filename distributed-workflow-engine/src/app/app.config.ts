// export class AppConfig {
//   public readonly userSignup = "http://172.23.238.176:8088/v0.1/userinfo/user";
//   public readonly login = "http://172.23.238.176:8087";
//   public readonly triggerEngine = "http://172.23.238.186:8021/v1.0/workflowname/";
//   public readonly saveWorkflow = "http://172.23.238.151:8080/v1.0/persistence/workflow/";
//   public readonly removeWorkflow = "http://172.23.238.151:8080/v1.0/persistence/workflow/remove/";
//   public readonly reportGetJobId = "http://172.23.238.151:8080/v1.0/persistence/jobdetails/userName/latest/";
//   public readonly getReport = "http://172.23.238.176:8080/checkout/";
//   public readonly persistence = "http://172.23.238.151:8080/v1.0/persistence/workflow/";
//   public readonly socket = "http://172.23.238.216:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = "http://172.23.238.151:8080/v1.0/persistence/workflow/users/";
//   public readonly getWorkflow = "http://172.23.238.151:8080/v1.0/persistence/workflows/workflow/";
//   public readonly getTasksOfWorkflow = "http://172.23.238.151:8080/v1.0/persistence/tasks/";
//}

export class AppConfig {
  public readonly userSignup = "http://api-gateway:8020/user-persistence/v0.1/userinfo/user";
  public readonly login = "http://api-gateway:8020/auth";
  public readonly triggerEngine = "http://api-gateway:8020/job-manager/v1.0/workflowname/";
  public readonly saveWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/";
  public readonly removeWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/remove/";
  public readonly reportGetJobId = "http://api-gateway:8020/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
  public readonly getReport = "http://api-gateway:8020/reporting-service/checkout/";
  public readonly persistence = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/";
  public readonly socket = "http://socket-service:9000/gs-guide-websocket";
  public readonly getAllWorkflows = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/users/";
  public readonly getWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflows/workflow/";
  public readonly getTasksOfWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/tasks/";
}
