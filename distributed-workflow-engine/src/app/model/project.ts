export class Project{
    name: String;
    workflows: Array<String>;
    description: String;
    constructor(name, description,workflows){
        this.name = name;
        this.workflows = workflows;
        this.description = description;
    }
}