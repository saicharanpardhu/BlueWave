//using IP of system
export class AppConfig {
  public readonly userSignup = "http://172.23.238.176:8088/v0.1/userinfo/user";
  public readonly login = "http://172.23.238.176:8087";
  public readonly triggerEngine = "http://172.23.238.186:8021/v1.0/workflowname/";
  public readonly saveWorkflow = "http://172.23.238.189:8055/v1.0/persistence/workflow/";
  public readonly removeWorkflow = "http://172.23.238.189:8055/v1.0/persistence/workflow/remove/";
  public readonly reportGetJobId = "http://172.23.238.189:8055/v1.0/persistence/jobdetails/userName/latest/";
  public readonly getReport = "http://172.23.238.176:8188/checkout/";
  public readonly persistence = "http://172.23.238.189:8055/v1.0/persistence/workflow/";
  public readonly socket = "http://172.23.238.216:9000/gs-guide-websocket";
  public readonly getAllWorkflows = "http://172.23.238.189:8055/v1.0/persistence/workflow/users/";
  public readonly getWorkflow = "http://172.23.238.189:8055/v1.0/persistence/workflows/workflow/";
  public readonly getTasksOfWorkflow = "http://172.23.238.189:8055/v1.0/persistence/tasks/";
  public readonly getTaskNumber = "http://172.23.238.189:8055/v1.0/persistence/count/jobdetails/";
}

// using container name 
// export class AppConfig {
//   public readonly userSignup = "http://api-gateway:8020/user-persistence/v0.1/userinfo/user";
//   public readonly login = "http://api-gateway:8020/auth";
//   public readonly triggerEngine = "http://api-gateway:8020/job-manager/v1.0/workflowname/";
//   public readonly saveWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly removeWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/remove/";
//   public readonly reportGetJobId = "http://api-gateway:8020/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
//   public readonly getReport = "http://api-gateway:8020/reporting-service/checkout/";
//   public readonly persistence = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly socket = "http://socket-service:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflow/users/";
//   public readonly getWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/workflows/workflow/";
//   public readonly getTasksOfWorkflow = "http://api-gateway:8020/persistence-manager/v1.0/persistence/tasks/";
//   public readonly getTaskNumber = "http://api-gateway:8020/persistence-manager/v1.0/persistence/count/jobdetails/";
// }

//using external IP running on docker
//  export class AppConfig {
//   public readonly userSignup = "http://172.23.238.151:8020/user-persistence/v0.1/userinfo/user";
//  // public readonly login = "http://172.23.238.151:8020/auth";
//   public readonly login = "http://172.23.238.151:8087";
//   public readonly triggerEngine = "http://172.23.238.151:8020/job-manager/v1.0/workflowname/";
//   public readonly saveWorkflow = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly removeWorkflow = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/workflow/remove/";
//   public readonly reportGetJobId = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
//   public readonly getReport = "http://172.23.238.151:8020/reporting-service/checkout/";
//   public readonly persistence = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly socket = "http://172.23.238.151:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/workflow/users/";
//   public readonly getWorkflow = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/workflows/workflow/";
//   public readonly getTasksOfWorkflow = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/tasks/";
//   public readonly getTaskNumber = "http://172.23.238.151:8020/persistence-manager/v1.0/persistence/count/jobdetails/";
// }


//Running on AWS
// export class AppConfig {
//   public readonly userSignup = "http://35.154.244.122:8020/user-persistence/v0.1/userinfo/user";
//   public readonly login = "http://35.154.244.122:8087";
//   public readonly triggerEngine = "http://35.154.244.122:8020/job-manager/v1.0/workflowname/";
//   public readonly saveWorkflow = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly removeWorkflow = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/workflow/remove/";
//   public readonly reportGetJobId = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
//   public readonly getReport = "http://35.154.244.122:8020/reporting-service/checkout/";
//   public readonly persistence = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/workflow/";
//   public readonly socket = "http://35.154.244.122:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/workflow/users/";
//   public readonly getWorkflow = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/workflows/workflow/";
//   public readonly getTasksOfWorkflow = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/tasks/";
//   public readonly getTaskNumber = "http://35.154.244.122:8020/persistence-manager/v1.0/persistence/count/jobdetails/";
// }

