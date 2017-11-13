import {Task} from "./task";

export class Workflow{

workFlowName:String ; 	
owner:String ;     	
workFlowStatus:String ;    
tasks:Map<String,Task>;

}