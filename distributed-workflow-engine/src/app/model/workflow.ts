import { Task } from "./task";

export class WorkFlow{
    workFlowName:String;
    owner: String;
    canViewUser: Array<String>;
    canEditUser: Array<String>;
    workFlowStatus: String;
    tasks: Map<String,Task>;

    constructor(workFlowName, owner, canViewUser, canEditUser, workFlowStatus, tasks){
        this.workFlowName = workFlowName;
        this.owner= owner;
        this.canViewUser = canViewUser;
        this.canEditUser = canEditUser;
        this.workFlowStatus = workFlowStatus;
        this.tasks = tasks;
    }
}