import { Task } from "./task";

export class WorkFlow{
    workFlowName:String;
    owner: String;
    canViewUser: Array<String>;
    canEditUser: Array<String>;
    workFlowStatus: String;
    tasks: Map<String,Task>;    
    description : String;

    constructor(workFlowName, owner, description, canViewUser, canEditUser, workFlowStatus, tasks){
        this.workFlowName = workFlowName;
        this.owner= owner;
        this.description = description;
        this.canViewUser = canViewUser;
        this.canEditUser = canEditUser;
        this.workFlowStatus = workFlowStatus;
        this.tasks = tasks;
    }
}