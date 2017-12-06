//https://github.com/jaydeepjain3226/movie_cruiser_springboot.git
//https://github.com/avalanche557/Spring_restapi.git
//using IP of system
// export class AppConfig {
//   public readonly userSignup = "http://172.23.238.176:8088/v0.1/userinfo/user/";
//   public readonly login = "http://172.23.238.176:8087/";
//   public readonly triggerEngine = "http://172.23.238.186:8021/v1.0/workflowname/";
//   public readonly persistenceUrl = "http://172.23.238.189:8055/v1.0/persistence/";
//   public readonly saveWorkflow = this.persistenceUrl + "workflow/";
//   public readonly removeWorkflow = this.persistenceUrl + "workflow/remove/";
//   public readonly reportGetJobId = this.persistenceUrl + "jobdetails/userName/latest/";
//   public readonly getReport = "http://172.23.238.176:8188/checkout/";
//   public readonly persistence = this.persistenceUrl + "workflow/";
//   public readonly socket = "http://172.23.238.216:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = this.persistenceUrl + "workflow/users/";
//   public readonly getWorkflow = this.persistenceUrl + "workflows/workflow/";
//   public readonly getTasksOfWorkflow = this.persistenceUrl + "tasks/";
//   public readonly getTaskNumber = this.persistenceUrl + "count/jobdetails/";
// }


// export class AppConfig {
//   public readonly userSignup = "http://172.23.238.178:8090/user-persistence/v0.1/userinfo/user";
//   public readonly login = "http://172.23.238.178:5000";
//   public readonly triggerEngine = "http://172.23.238.178:8090/job-manager/v1.0/workflowname/";
//   public readonly saveWorkflow = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/workflow/";
//   public readonly removeWorkflow = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/workflow/remove/";
//   public readonly reportGetJobId = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
//   public readonly getReport = "http://172.23.238.178:8040/checkout/";
//   public readonly persistence = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/workflow/";
//   public readonly socket = "http://172.23.238.178:3000/gs-guide-websocket";
//   public readonly getAllWorkflows = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/workflow/users/";
//   public readonly getWorkflow = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/workflows/workflow/";
//   public readonly getTasksOfWorkflow = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/tasks/";
//   public readonly getTaskNumber = "http://172.23.238.178:8090/persistence-manager/v1.0/persistence/count/jobdetails/";
// }
//export class AppConfig {
  //public readonly userSignup = "http://172.23.238.151:8090/user-persistence/v0.1/userinfo/user";
  //public readonly login = "http://172.23.238.151:5000";
  //public readonly triggerEngine = "http://172.23.238.151:8090/job-manager/v1.0/workflowname/";
  //public readonly saveWorkflow = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/workflow/";
  //public readonly removeWorkflow = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/workflow/remove/";
  //public readonly reportGetJobId = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
  //public readonly getReport = "http://172.23.238.151:8040/checkout/";
  //public readonly persistence = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/workflow/";
  //public readonly socket = "http://172.23.238.151:3000/gs-guide-websocket";
  //public readonly getAllWorkflows = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/workflow/users/";
  //public readonly getWorkflow = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/workflows/workflow/";
  //public readonly getTasksOfWorkflow = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/tasks/";
  //public readonly getTaskNumber = "http://172.23.238.151:8090/persistence-manager/v1.0/persistence/count/jobdetails/";
//}

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
// export class AppConfig {
//   public readonly userSignup = "http://172.23.238.176:8088/v0.1/userinfo/user/";
//   public readonly login = "http://172.23.238.176:8087/aaa";
//   public readonly triggerEngine = "http://172.23.238.186:8021/v1.0/workflowname/";
//   public readonly persistenceUrl = "http://172.23.238.151:8055/v1.0/persistence/";
//   public readonly saveWorkflow = this.persistenceUrl + "workflow/";
//   public readonly removeWorkflow = this.persistenceUrl + "workflow/remove/";
//   public readonly reportGetJobId = this.persistenceUrl + "jobdetails/userName/latest/";
//   public readonly getReport = "http://172.23.238.176:8188/checkout/";
//   public readonly persistence = this.persistenceUrl + "workflow/";
//   public readonly socket = "http://172.23.238.216:9000/gs-guide-websocket";
//   public readonly getAllWorkflows = this.persistenceUrl + "workflow/users/";
//   public readonly getWorkflow = this.persistenceUrl + "workflows/workflow/";
//   public readonly getTasksOfWorkflow = this.persistenceUrl + "tasks/";
//   public readonly getTaskNumber = this.persistenceUrl + "count/jobdetails/";
// }

//Running on AWS
 export class AppConfig {
  public readonly userSignup = "http://35.154.244.122:8090/user-persistence/v0.1/userinfo/user";
   public readonly login = "http://35.154.244.122:5000";
   public readonly triggerEngine = "http://35.154.244.122:8090/job-manager/v1.0/workflowname/";
   public readonly saveWorkflow = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/";
   public readonly removeWorkflow = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/remove/";
   public readonly reportGetJobId = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
   public readonly getReport = "http://35.154.244.122:8090/reporting-service/checkout/";
   public readonly persistence = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/";
   public readonly socket = "http://35.154.244.122:3000/gs-guide-websocket";
   public readonly getAllWorkflows = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/users/";
   public readonly getWorkflow = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflows/workflow/";
   public readonly getTasksOfWorkflow = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/tasks/";
   public readonly getTaskNumber = "http://35.154.244.122:8090/persistence-manager/v1.0/persistence/count/jobdetails/";
 }


//  export class AppConfig {
//   public readonly userSignup = "https://35.154.244.122:8090/user-persistence/v0.1/userinfo/user";
//    public readonly login = "https://35.154.244.122:5000";
//    public readonly triggerEngine = "https://35.154.244.122:8090/job-manager/v1.0/workflowname/";
//    public readonly saveWorkflow = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/";
//    public readonly removeWorkflow = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/remove/";
//    public readonly reportGetJobId = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/jobdetails/userName/latest/";
//    public readonly getReport = "https://35.154.244.122:8090/reporting-service/checkout/";
//    public readonly persistence = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/";
//    public readonly socket = "https://35.154.244.122:3000/gs-guide-websocket";
//    public readonly getAllWorkflows = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflow/users/";
//    public readonly getWorkflow = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/workflows/workflow/";
//    public readonly getTasksOfWorkflow = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/tasks/";
//    public readonly getTaskNumber = "https://35.154.244.122:8090/persistence-manager/v1.0/persistence/count/jobdetails/";
//  }

