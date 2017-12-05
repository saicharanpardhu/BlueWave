export class Task{
    taskType: String;
    status: String;
    depends_on: Array<String>
    output: Array<String>
    input: Array<String>
    
    constructor(taskType, status, depends_on, output, input ){
        this.input = input;
        this.depends_on = depends_on;
        this.taskType = taskType;
        this.status = status;
        this.output = output;
    }
}