import { PerisitenceService } from "./../../services/persistence/perisitence.service";
import { WorkflowDetailsService } from "./../../services/workflow-details/workflow-details.service";
import { Router } from "@angular/router";
import { AuthenticationService } from "./../../services/authentication/authentication.service";
import {
  Component,
  OnInit,
  Inject,
  ViewEncapsulation,
  ViewChild
} from "@angular/core";
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatSnackBar,
  MatSnackBarConfig
} from "@angular/material";
import { FormControl } from "@angular/forms";
import * as shape from "d3-shape";
import { colorSets } from "./models/color-sets";
import {
  countries,
  generateHierarchialGraph,
  getTurbineData
} from "./models/data";
import chartGroups from "./models/chartTypes";
import { id } from "./models/id";
import { Task } from "./models/task";
import { Workflow } from "./models/workflow";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatChipInputEvent } from "@angular/material";
import "rxjs/add/operator/filter";
import "rxjs/add/operator/map";
import { TagInputModule } from "ngx-chips";
import "rxjs/add/operator/debounceTime";
import { MatRadioModule } from "@angular/material/radio";
import {
  JsonEditorComponent,
  JsonEditorOptions
} from "angular4-jsoneditor/jsoneditor/jsoneditor.component";
import { TSMap } from "typescript-map";

import { OnDestroy } from "@angular/core";
import {NgcFloatButtonModule} from 'ngc-float-button';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

/**
 * @title Dialog Overview
 */

@Component({
  selector: "app-create-workflow",
  encapsulation: ViewEncapsulation.None,
  templateUrl: "./create-workflow.component.html",
  styleUrls: ["./create-workflow.component.css"]
})
export class CreateWorkflowComponent implements OnInit, OnDestroy {
  //Task Library
  public tasks = [
    {
      name: "Clone",
      value: "Clone"
    },
    {
      name: "Uppercase",
      value: "upperCase"
    },
    {
      name: "Build",
      value: "Build1"
    },
    {
      name: "Run",
      value: "run"
    }
  ];
  //Tasks of the workflow
  map = new TSMap<String, Task>();
  //Dummy workflow
  workflow: Workflow = {};
  task: Task = {};
  //wORKFLOW Name

  Wname  :String="";
  taskName :String;
  type : String;
  dependsOn : any;
  depends_on : any;
  input :String;

  //workFlow Status
  status = "created";
  //List of all aliases in a workflow
  wTaskAliases: String[] = [];
  //DEletion Mode

  deleteMode : boolean = false;
  deleteModeButton ="DELETE TASKS";
  inputType = 'None';


  loadWorkflow = false;

  // with 'true' our FAB will be started open.
  private open:BehaviorSubject<boolean> = new BehaviorSubject(true); // true is the initial state of FAB

  //Chart properties
  version = "APP_VERSION";
  theme = "dark";
  chartType = "directed-graph";
  chartGroups: any;
  chart: any;
  realTimeData: boolean = false;
  countries: any[];
  graph: { links: any[]; nodes: any[] };
  hierarchialGraph: { links: any[]; nodes: any[] };
  view: any[];
  width: number = 700;
  height: number = 300;
  fitContainer: boolean = true;
  autoZoom: boolean = true;
  // options
  showLegend = false;
  orientation: string = "LR"; // LR, RL, TB, BT
  orientations: any[] = [
    {
      label: "Left to Right",
      value: "LR"
    },
    {
      label: "Right to Left",
      value: "RL"
    },
    {
      label: "Top to Bottom",
      value: "TB"
    },
    {
      label: "Bottom to Top",
      value: "BT"
    }
  ];
  // line interpolation
  curveType: string = "Step";
  curve: any = shape.curveLinear;
  interpolationTypes = [
    "Bundle",
    "Cardinal",
    "Catmull Rom",
    "Linear",
    "Monotone X",
    "Monotone Y",
    "Natural",
    "Step",
    "Step After",
    "Step Before"
  ];

  //Colorset options
  colorSets: any;
  colorSets1 = [
    "vivid",
    "natural",
    "cool",
    "fire",
    "solar",
    "air",
    "aqua",
    "flame",
    "ocean",
    "forest",
    "horizon",
    "neons",
    "picnic",
    "night",
    "nightLights"
  ];
  colorScheme: any;
  schemeType: string = "ordinal";
  selectedColorScheme: string;


  

  public constructor(
    public dialog: MatDialog, 
    private authentication : AuthenticationService,

    private persistence: PerisitenceService,
    private router: Router,
    private workflowService: WorkflowDetailsService,
    private snackBar: MatSnackBar
  ) {
    
   
   Object.assign(this, {
      countries,
      colorSets,
      chartGroups,

      hierarchialGraph: getTurbineData(),
    })
    this.setColorScheme('picnic')    
    this.setInterpolationType('Bundle')

  }

  //Display a worflow if user comes to view
  ngOnInit() {
    
    this.selectChart(this.chartType);
    this.selectedColorScheme = "aqua";
    this.updateNodes("Start");

    if (!this.fitContainer) {
      this.applyDimensions();
    }
    if (this.workflowService.displayWorkflow != null) {
      this.loadWorkflow = true;
      console.log(this.loadWorkflow);
      console.log(this.workflowService.displayWorkflow.tasks);

          this.map.fromJSON( this.workflowService.displayWorkflow.tasks);
          this.wTaskAliases = this.map.keys();
          this.workflowToGraph(this.map);
          this.workflow.workFlowName = this.workflowService.displayWorkflow.workFlowName;
      console.log(this.map);    
    }
    else{
      this.openWnameDialog(false);

    }
  }

  //DEstroy the workflow that the user viewed
  ngOnDestroy() {
    this.workflowService.displayWorkflow = null;
  }

  //Update nodes to add a node to workflow
  updateNodes(taskname: String) {
    let len2 = this.hierarchialGraph.nodes.length;
    console.log("update node",len2);
    

    const hNode = {
      id: id(),
      label: taskname
    };
    this.hierarchialGraph.nodes.push(hNode);
    this.hierarchialGraph.links = [...this.hierarchialGraph.links];
    this.hierarchialGraph.nodes = [...this.hierarchialGraph.nodes];
    
  }
  //update links when add a node
  updateLinks(depends: String[], taskname: String) {
    let target: any;
    let len = depends.length;
    let len2 = this.hierarchialGraph.nodes.length;

    for (let i = 0; i < len2; i++) {
      if (this.hierarchialGraph.nodes[i].label == taskname) {
        target = this.hierarchialGraph.nodes[i].id;
      }
    }

    for (let i = 0; i < len2; i++) {
      for (let j = 0; j < len; j++) {
        if (this.hierarchialGraph.nodes[i].label == depends[j]) {
          this.hierarchialGraph.links.push({
            source: this.hierarchialGraph.nodes[i].id,
            target: target,
            label: "on success"
          });
          console.log("ggghjhjhjkkk");
        }
      }
    }
    this.hierarchialGraph.links = [...this.hierarchialGraph.links];
    this.hierarchialGraph.nodes = [...this.hierarchialGraph.nodes];
  }

  applyDimensions() {
    this.view = [this.width, this.height];
  }
  toggleFitContainer(fitContainer: boolean, autoZoom: boolean): void {
    this.fitContainer = fitContainer;
    this.autoZoom = autoZoom;
    if (this.fitContainer) {
      this.view = undefined;
    } else {
      this.applyDimensions();
    }
  }
  selectChart(chartSelector) {
    this.chartType = chartSelector;
    for (const group of this.chartGroups) {
      for (const chart of group.charts) {
        if (chart.selector === chartSelector) {
          this.chart = chart;
          return;
        }
      }
    }
  }

  //Enter or exit deletion mode
  deletemode() {
    if (!this.deleteMode) {
      this.deleteMode = true;
      let config = new MatSnackBarConfig();
      config.duration = 3000;
      this.snackBar.open(
        "You are in deletion mode.Click on the nodes to delete them",
        "Close",
        config
      );

      this.deleteModeButton = "EXIT MODE";
    } else {
      this.deleteMode = false;
      let config = new MatSnackBarConfig();
      config.duration = 3000;
      this.snackBar.open("You are out of deletion mode", "Close", config);
      this.deleteModeButton = "DELETE TASKS";
    }
  }
  //On select a node to delete
  select(data) {
    console.log("Item clicked", data);
    if (this.deleteMode) {
      this.deleteTask(data.label);
    }
    else{
        let task = this.map.get(data.label);
        if(task.input[0] == null)
        this.openTaskEditDialog(data.label,task,"None")  ; 
        if(task.input[0] != null)
        this.openTaskEditDialog(data.label,task,"Custom")  ; 
    }
  }


  openTaskEditDialog(taskName:String,task:any,inputType:String):void{
    
        let dialogRef = this.dialog.open(DialogOverviewDialog, {
          width: '500px',
          data: { taskName: taskName, type:task.type ,dependsOn:task.depends_on,input:task.input,taskAliases:this.wTaskAliases,taskTypes:this.tasks,inputType:inputType,editing:true,cancel:false,taskNameOld:taskName}
    
        });
        dialogRef.afterClosed().subscribe(result => {
          
          if(result!=undefined){
          this.deleteTask(taskName);
          let task: Task = {};
          this.taskName = result.taskName;
          task.type = result.type;
          this.depends_on = result.dependsOn;
          task.input = [];
          task.status = "ready";
          task.input.push(result.input);
          this.wTaskAliases.push(result.taskName);
    
          task.depends_on = null;
    
          this.updateNodes(this.taskName);
          if (this.depends_on != undefined) {
            task.depends_on = [];
            let len2 = this.depends_on.length;
            for (let i = 0; i < len2; i++) {
              if (JSON.parse(JSON.stringify(result.dependsOn[i])).value != null) {
                task.depends_on.push(
                  JSON.parse(JSON.stringify(result.dependsOn[i])).value
                );
              } else {
                task.depends_on.push(
                  JSON.stringify(result.dependsOn[i]).replace(/\"/g, "")
                );
              }
            }
    
            this.updateLinks(task.depends_on, this.taskName);
          } else {
            let start_depends = [];
            start_depends.push("Start");
            this.updateLinks(start_depends, this.taskName);
          }
    
          console.log(this.taskName);
          this.map.set(this.taskName, task);
          this.workflow.tasks = this.map;
          console.log(this.map);
        }
        });
    
    
     }         

  //Delete a task and it's links
  deleteTask(label: String) {
    console.log(label);
    console.log(this.map);
    this.map.delete(label);
    this.wTaskAliases = this.wTaskAliases.filter(item => item != label);
    console.log(this.map);
    this.map.forEach((value: Task, key: String) => {
      value.depends_on = value.depends_on.filter(item => item != label);
      value.input = value.input.filter(item => item != label);
    });
    this.workflow.tasks = this.map;
    this.workflowToGraph(this.map);
  }
  //Convert any map to links
  workflowToGraph(map: TSMap<String, Task>) {
    this.hierarchialGraph = getTurbineData();
    this.updateNodes("Start");
    let len = this.wTaskAliases.length;
    for (let i = 0; i < len; i++) {
      this.updateNodes(this.wTaskAliases[i]);

      if (map.get(this.wTaskAliases[i]).depends_on != null) {
        this.updateLinks(
          map.get(this.wTaskAliases[i]).depends_on,
          this.wTaskAliases[i]
        );
        console.log(
          map.get(this.wTaskAliases[i]).depends_on,
          this.wTaskAliases[i]
        );
      } else {
        let start_depends = [];
        start_depends.push("Start");
        this.updateLinks(start_depends, this.wTaskAliases[i]);
      }
    }
  }

  //To set a color scheme
  setColorScheme(name) {
    this.selectedColorScheme = name;
    this.colorScheme = this.colorSets.find(s => s.name === name);
  }

  // To SET A CURVE TYPE
  setInterpolationType(curveType) {
    this.curveType = curveType;
    if (curveType === "Bundle") {
      this.curve = shape.curveBundle.beta(1);
    }
    if (curveType === "Cardinal") {
      this.curve = shape.curveCardinal;
    }
    if (curveType === "Catmull Rom") {
      this.curve = shape.curveCatmullRom;
    }
    if (curveType === "Linear") {
      this.curve = shape.curveLinear;
    }
    if (curveType === "Monotone X") {
      this.curve = shape.curveMonotoneX;
    }
    if (curveType === "Monotone Y") {
      this.curve = shape.curveMonotoneY;
    }
    if (curveType === "Natural") {
      this.curve = shape.curveNatural;
    }
    if (curveType === "Step") {
      this.curve = shape.curveStep;
    }
    if (curveType === "Step After") {
      this.curve = shape.curveStepAfter;
    }
    if (curveType === "Step Before") {
      this.curve = shape.curveStepBefore;
    }
  }
  onLegendLabelClick(entry) {
    console.log("Legend clicked", entry);
  }
  toggleExpand(node) {
    console.log("toggle expand", node);
  }

 





  //Dialog to add a task
  openDialog(): void {
    let dialogRef = this.dialog.open(DialogOverviewDialog, {
      width: '500px',
      data: { taskName: this.taskName, type:this.type ,dependsOn:this.dependsOn,input:this.input,taskAliases:this.wTaskAliases,taskTypes:this.tasks,inputType:this.inputType}

    });
    dialogRef.afterClosed().subscribe(result => {
      let task: Task = {};
      this.taskName = result.taskName;
      task.type = result.type;
      this.depends_on = result.dependsOn;
      task.input = [];
      task.status = "ready";
      task.input.push(result.input);
      this.wTaskAliases.push(result.taskName);

      task.depends_on = null;

      this.updateNodes(this.taskName);
      if (this.depends_on != undefined) {
        task.depends_on = [];
        let len2 = this.depends_on.length;
        for (let i = 0; i < len2; i++) {
          if (JSON.parse(JSON.stringify(result.dependsOn[i])).value != null) {
            task.depends_on.push(
              JSON.parse(JSON.stringify(result.dependsOn[i])).value
            );
          } else {
            task.depends_on.push(
              JSON.stringify(result.dependsOn[i]).replace(/\"/g, "")
            );
          }
        }

        this.updateLinks(task.depends_on, this.taskName);
      } else {
        let start_depends = [];
        start_depends.push("Start");
        this.updateLinks(start_depends, this.taskName);
      }

      console.log(this.taskName);
      this.map.set(this.taskName, task);
      this.workflow.tasks = this.map;
      console.log(this.map);
    });
  }
  executeWorkflow() {
    this.updateWorkflow().then(() =>
      this.persistence.triggerEngine(this.workflow.workFlowName)
    );
  }
  //Save workflow to DB
  saveWorkflow() {
    this.workflow.owner = localStorage.getItem("Email");
    let config = new MatSnackBarConfig();
    config.duration = 500;
    console.log("From component: ", this.workflow.workFlowName);
    return this.persistence
      .sendWorkFlow2(
        this.workflow.workFlowName,
        this.workflow.owner,
        this.status,
        this.map
      )
      .then(() => {
        this.snackBar.open("Workflow saved successfully.", "Close", {
          duration: 2000
        });
        console.log(this.map);
      });
  }

  updateWorkflow() {
    this.workflow.owner = localStorage.getItem("Email");
    let config = new MatSnackBarConfig();
    config.duration = 500;
    console.log("From component: ", this.workflow.workFlowName);
    return this.persistence
      .updateWorkFlow(
        this.workflow.workFlowName,
        this.workflow.owner,
        this.status,
        this.map
      )
      .then(() => {
        console.log("Should have opened snackbar");
        this.snackBar.open("Workflow saved successfully.", "Close", {
          duration: 2000
        });
      });
  }


// Dialog for the workflow name
    openWnameDialog(boolean:Boolean): void {
    let dialogRef = this.dialog.open(WnameOverviewDialog, {
      width: '300px',
      data: { Wname: this.Wname ,editing:boolean},
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe(result => {
      
      this.workflow.workFlowName=result;
      this.Wname = result;
      console.log(this.workflow.workFlowName,'The dialog was closed');
      
      

    });
  }

  //Dialog for jsoneditor
  openjsoneditor(): void {
    let json = this.map.toJSON();
    let dialogRef = this.dialog.open(JsonEditor, {
      data: { json: json },
      width: "700px",
      height: "600px"
    });
    dialogRef.afterClosed().subscribe(result => {
      let myMap = new TSMap<String, Task>();
      myMap.fromJSON(result);
      console.log(myMap);
      this.map = myMap;

      this.wTaskAliases = this.map.keys();

      this.workflowToGraph(this.map);
    });
  }

  //Dialog for settings of the graph
  openSettings(): void {
    let dialogRef = this.dialog.open(SettingsDialog, {
      data: {
        orientations: this.orientations,
        orientation: this.orientation,
        colors: this.colorSets1,
        color: this.selectedColorScheme,
        curveTypes: this.interpolationTypes,
        curveType: this.curveType
      },
      width: "300px"
    });
    dialogRef.afterClosed().subscribe(result => {
      this.orientation = result.orientation;
      this.setColorScheme(result.color);
      this.setInterpolationType(result.curveType);
    });
  }
}
@Component({
  selector: "dialog-overview-dialog",
  templateUrl: "dialog-overview-dialog.html",
  styleUrls: ["dialog-overview-dialog.css"]
})
export class DialogOverviewDialog {

  
   myForm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private snackBar:MatSnackBar,
    private fb: FormBuilder) { } 
    
  onNoClick(): void { 

     let close:boolean;
     close = true;
     
     this.myForm = this.fb.group({
    options: ['1']
  })


    if (this.data.taskName == null || this.data.type == null) {
      let config = new MatSnackBarConfig();
      config.duration = 3000;
      this.snackBar.open("Please give taskName and taskType", "Close", config);
    }
    if (
      this.data.type == "Clone" ||
      this.data.type == "Build" ||
      this.data.type == "Run" ||
      this.data.type == "test2"
    ) {
      if (
        this.data.type == "Clone" ||
        this.data.type == "Build" ||
        this.data.type == "Run"
      ) {
        let config = new MatSnackBarConfig();
        config.duration = 3000;
        if(this.data.input==undefined)
        this.snackBar.open("Please give task input", "Close", config);
        if (
          this.data.input.match(
            "(\\w+://)(.+@)*([\\w\\d\\.]+)(:[\\d]+){0,1}/*(.*)"
          ) &&
          this.data.input.match("(.+@)*([\\w\\d\\.]+):(.*)")
        ) {
          close = true;
        } else {
          close = false;
          this.snackBar.open(
            "Please give proper input for task type",
            this.data.type
          );
        }
      }
    }

    if(this.data.editing)
      
      this.data.taskAliases =this.data.taskAliases.filter(item => item!=this.data.taskNmaeOld);
      
              
            
    let len = this.data.taskAliases.length;

    for (let i = 0; i < len; i++) {
      if (this.data.taskName == this.data.taskAliases[i]) {
        close = false;

        let config = new MatSnackBarConfig();
        config.duration = 3000;
        this.snackBar.open("Task name already exists", "Close", config);
      }
    }
    if (this.data.taskName != null && this.data.type != null && close)
      this.dialogRef.close(this.data);
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }
}
@Component({
  selector: "wname-overview-dialog",
  templateUrl: "wname-overview-dialog.html"
})

export class WnameOverviewDialog implements OnInit{
  
   allWorkflowNames :any;
   workflowNameexists :boolean = false;
   wname :String ="";

  constructor(
    public dialogRef: MatDialogRef<WnameOverviewDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private persistence: PerisitenceService,
  private snackBar:MatSnackBar,
  private router: Router) { }
   ngOnInit(){
    
    this.wname=this.data.Wname;  

   }
  


  onNoClick(): void {
    if (this.data.Wname == null) {
      let config = new MatSnackBarConfig();
      config.duration = 3000;
      this.snackBar.open("Please give a name to the workflow", "Close", config);
    }

    if (this.data.Wname != null&&!this.workflowNameexists)
      this.dialogRef.close(this.data.Wname);
  }

 onCancelClick(): void{
   if(this.data.editing)
      this.data.Wname = this.wname;
      this.dialogRef.close(this.data.Wname);
   if(!this.data.editing) 
   {
     this.dialogRef.close();
     let link = ['/home'];
    this.router.navigate(link);
   }
}
  workflowNameValidator = new FormControl('', [Validators.required]); 
  getworkflowNameErrorMessage() { 
    return this.workflowNameValidator.hasError('required') ? 'Workflow name required' : '';
  } 

  

    searchWorkflowName(wname:String){
       
           this.workflowNameexists=false;

        console.log(wname);
        if(this.allWorkflowNames!=undefined){
             
               let len = this.allWorkflowNames.length;
               for(let i=0;i<len;i++){
                    console.log(this.allWorkflowNames[i]["workFlowName"]);
                    if(wname==this.allWorkflowNames[i]["workFlowName"])
                        {
                          this.workflowNameexists = true;
                          
                        }
                   


               }
               console.log(this.workflowNameexists);
           
        }
        else{
              this.allWorkflowNames =[];
               let username = localStorage.getItem("Email");
               this.persistence.getWorkflowNames(username).subscribe(
                result =>{
                  this.allWorkflowNames=result.json();
                  
                } );


               let len = this.allWorkflowNames.length;
               for(let i=0;i<len;i++){

                    if(wname=this.allWorkflowNames[i]["workFlowName"])
                        this.workflowNameexists = true

               }
        }



}}

@Component({
  selector: "jsoneditor-overview-dialog",
  templateUrl: "jsoneditor-overview-dialog.html",
  styleUrls: ["jsoneditor-overview-dialog.css"]
})
export class JsonEditor {
  public editorOptions: JsonEditorOptions;
  public jsondata: any;
  @ViewChild(JsonEditorComponent) editor: JsonEditorComponent;
  constructor(
    public dialogRef: MatDialogRef<JsonEditor>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.editorOptions = new JsonEditorOptions();
    this.editorOptions.modes = ["code", "text", "tree", "view"];
    this.jsondata = {
      products: [
        {
          name: "car",
          product: [
            {
              name: "honda",
              model: [
                { id: "civic", name: "civic" },
                { id: "accord", name: "accord" },
                { id: "crv", name: "crv" },
                { id: "pilot", name: "pilot" },
                { id: "odyssey", name: "odyssey" }
              ]
            }
          ]
        }
      ]
    };
  }

  onNoClick(): void {
    console.log(this.editor.get());
    this.dialogRef.close(this.editor.get());
  }

  onCancelClick(): void {
    this.dialogRef.close(this.data.json);
  }
}


@Component({
  selector: "settings-overview-dialog",
  templateUrl: "settings-overview-dialog.html",
  styleUrls: ["settings-overview-dialog.css"]
})
export class SettingsDialog {
  constructor(
    public dialogRef: MatDialogRef<SettingsDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  onNoClick(): void {
    this.dialogRef.close(this.data);
  }
}
