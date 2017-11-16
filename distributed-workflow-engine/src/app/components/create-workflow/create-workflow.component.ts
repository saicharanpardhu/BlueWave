import { PerisitenceService } from './../../services/persistence/perisitence.service';
import { WorkflowDetailsService } from './../../services/workflow-details/workflow-details.service';

import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication/authentication.service';
 import {Component, OnInit, Inject,ViewEncapsulation,ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { FormControl } from '@angular/forms';
import * as shape from 'd3-shape';
import { colorSets } from './color-sets';
import { countries, generateHierarchialGraph, getTurbineData } from './data';
import chartGroups from './chartTypes';
import { id } from './id';
import {Task} from "./task";
import {Workflow} from "./workflow";
import { FormBuilder, FormGroup } from '@angular/forms';
import {MatChipInputEvent} from '@angular/material';
import 'rxjs/add/operator/filter'; 
import 'rxjs/add/operator/map';
import { TagInputModule } from 'ngx-chips';
import 'rxjs/add/operator/debounceTime';
import {MatRadioModule} from '@angular/material/radio';
import { JsonEditorComponent, JsonEditorOptions } from 'angular4-jsoneditor/jsoneditor/jsoneditor.component';
import { TSMap } from "typescript-map";
import { OnDestroy } from '@angular/core';
/**
 * @title Dialog Overview
 */ 
@Component({
  selector: 'app-create-workflow',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './create-workflow.component.html',
  styleUrls: ['./create-workflow.component.css']
})
export class CreateWorkflowComponent implements OnInit, OnDestroy{

  public tasks: Array<String> = ["git-clone","mvn-package"];
  // workflow: Array<Map<String,String>> = [];
  
  map = new TSMap<String,Task>();
  workflow :Workflow = {};
  task :Task ={};
  Wname  :String;
  taskName :String;
  taskType : String;
  dependsOn : any;
  depends_on : any;
  input :String;
  status = "created";                           //workFlow Status
  wTaskAliases : String[] = [];
  deleteMode : boolean = false;
  deleteModeButton ="DELETE TASKS"
  
  items = ['Javascript', 'Typescript'];
  autocompleteItems = ['Item1', 'item2', 'item3'];
  autocompleteItemsAsObjects = [
    {value: 'Item1', id: 0, extra: 0},
    {value: 'item2', id: 1, extra: 1},
    'item3'
];
  checkBox = [
    "git-clone",
    "mvn-test",
    "mvn-build"
  ];



  version = "APP_VERSION";
  theme = 'dark';
  chartType = 'directed-graph';
  chartGroups: any;
  chart: any;
  realTimeData: boolean = false;
  countries: any[];
  graph: { links: any[], nodes: any[] };
  hierarchialGraph: { links: any[], nodes: any[] };

  view: any[];
  width: number = 700;
  height: number = 300;
  fitContainer: boolean = true;
  autoZoom: boolean = true;

  // options
  showLegend = false;
  orientation: string = 'LR'; // LR, RL, TB, BT

  orientations: any[] = [
    {
      label: 'Left to Right',
      value: 'LR'
    }, {
      label: 'Right to Left',
      value: 'RL'
    }, {
      label: 'Top to Bottom',
      value: 'TB'
    }, {
      label: 'Bottom to Top',
      value: 'BT'
    }
  ];

  // line interpolation
  curveType: string = 'Step';
  curve: any = shape.curveLinear;
  interpolationTypes = [
    'Bundle', 'Cardinal', 'Catmull Rom', 'Linear', 'Monotone X',
    'Monotone Y', 'Natural', 'Step', 'Step After', 'Step Before'
  ];
  

  colorSets:any;
  colorSets1 =["vivid","natural","cool",'fire','solar','air','aqua','flame','ocean','forest','horizon','neons','picnic','night','nightLights'];
  colorScheme: any;
  schemeType: string = 'ordinal';
  selectedColorScheme: string;

  constructor(
    public dialog: MatDialog, 
    private authentication : AuthenticationService,
    private persistence: PerisitenceService,
    private router: Router,
    private workflowService: WorkflowDetailsService
  ) {

    Object.assign(this, {
      countries,
      colorSets,
      chartGroups,
      hierarchialGraph: getTurbineData(),
    });
   
    this.setColorScheme('picnic');
    this.setInterpolationType('Bundle');
  }


  ngOnInit() { 
       
      
    this.selectChart(this.chartType);

    /*setInterval(this.updateData.bind(this), 1000);*/
     this.selectedColorScheme = "aqua"; 
    
    //this.task.taskType="gitclopne"
    /*this.myMap.set("wefef",this.task);
    console.log(this.myMap.toJSON());*/
    if (!this.fitContainer) {
      this.applyDimensions();
    }
    if (this.workflowService.displayWorkflow!=null){
      console.log(this.workflowService.displayWorkflow.tasks);
          this.map.fromJSON( this.workflowService.displayWorkflow.tasks);
          this.wTaskAliases = this.map.keys();
          this.workflowToGraph(this.map);
      console.log(this.map);    
    }
    else{
      this.openWnameDialog()

    }




  }

  ngOnDestroy() {
    this.workflowService.displayWorkflow=null;
  }

  
updateNodes(taskname:String) {
    /*if (!this.realTimeData) {
      return;
    }*/

   /* const country = this.countries[Math.floor(Math.random() * this.countries.length)];
    const add = Math.random() < 0.7;
    const remove = Math.random() < 0.5;

    if (add) {*/
      // directed graph

      const hNode = {
        id:id(),
        label: taskname,
      };

      this.hierarchialGraph.nodes.push(hNode);

     /* let len = dependson.length;
      let len2 =  this.hierarchialGraph.nodes.length;
*/
    /* for(let i=0;i<len2;i++){

          for(let j=0;j<len;j++){

            if(this.hierarchialGraph.nodes[i].label==dependson[j])
                {
                  this.hierarchialGraph.links.push({
                   source: this.hierarchialGraph.nodes[i].id,
                   target: hNode.id,
                   label: 'on success'

      });
                  console.log("ggghjhjhjkkk");
        }
       }

       }*/
      this.hierarchialGraph.links = [...this.hierarchialGraph.links];
      this.hierarchialGraph.nodes = [...this.hierarchialGraph.nodes];
    }
  
  updateLinks(depends :String[],taskname :String){


      let target :any;

      let len = depends.length;
      let len2 =  this.hierarchialGraph.nodes.length;

      for(let i=0;i<len2;i++){

          if(this.hierarchialGraph.nodes[i].label==taskname)
          {

            target = this.hierarchialGraph.nodes[i].id;
          }

      }



     for(let i=0;i<len2;i++){

          for(let j=0;j<len;j++){

            if(this.hierarchialGraph.nodes[i].label==depends[j])
                {
                  this.hierarchialGraph.links.push({
                   source: this.hierarchialGraph.nodes[i].id,
                   target: target,
                   label: 'on success'

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
  
  deletemode(){
    if(!this.deleteMode){
        this.deleteMode = true;
    alert("You are in deletion mode.Click on the nodes to delete them");
     this.deleteModeButton = "EXIT MODE";
  }

    else{
          this.deleteMode = false;
    alert("You are out of deletion mode");
           this.deleteModeButton = "DELETE TASKS";
           //console.log(this.hierarchialGraph.links );
           //console.log(this.hierarchialGraph.nodes);
    }
  }

  select(data) {
    console.log('Item clicked', data);
    if(this.deleteMode){
      

     this.deleteTask(data.label);

    }
  }

  deleteTask(label:String){
       console.log(label)
       console.log(this.map);
      this.map.delete(label);
      this.wTaskAliases = this.wTaskAliases.filter(item => item != label);
      console.log(this.map);
      //console.log(this.wTaskAliases);
      this.workflow.tasks = this.map;
      this.workflowToGraph(this.map);

  }

workflowToGraph(map :TSMap<String,Task> ){

this.hierarchialGraph = getTurbineData();

console.log("YY");

//console.log(this.hierarchialGraph.links );
//console.log(this.hierarchialGraph.nodes);
let len =  this.wTaskAliases.length;

for(let i=0;i<len;i++){


      this.updateNodes(this.wTaskAliases[i]);
      
      if(map.get(this.wTaskAliases[i]).depends_on!=null){
      this.updateLinks(map.get(this.wTaskAliases[i]).depends_on,this.wTaskAliases[i]);
      console.log(map.get(this.wTaskAliases[i]).depends_on,this.wTaskAliases[i]);}

}


}


  setColorScheme(name) {
    this.selectedColorScheme = name;
    this.colorScheme = this.colorSets.find(s => s.name === name);
  }

  setInterpolationType(curveType) {
    this.curveType = curveType;
    if (curveType === 'Bundle') {
      this.curve = shape.curveBundle.beta(1);
    }
    if (curveType === 'Cardinal') {
      this.curve = shape.curveCardinal;
    }
    if (curveType === 'Catmull Rom') {
      this.curve = shape.curveCatmullRom;
    }
    if (curveType === 'Linear') {
      this.curve = shape.curveLinear;
    }
    if (curveType === 'Monotone X') {
      this.curve = shape.curveMonotoneX;
    }
    if (curveType === 'Monotone Y') {
      this.curve = shape.curveMonotoneY;
    }
    if (curveType === 'Natural') {
      this.curve = shape.curveNatural;
    }
    if (curveType === 'Step') {
      this.curve = shape.curveStep;
    }
    if (curveType === 'Step After') {
      this.curve = shape.curveStepAfter;
    }
    if (curveType === 'Step Before') {
      this.curve = shape.curveStepBefore;
    }
  }

  onLegendLabelClick(entry) {
    console.log('Legend clicked', entry);
  }

  toggleExpand(node) {
    console.log('toggle expand', node);
  }

  
  openDialog(): void {
    let dialogRef = this.dialog.open(DialogOverviewDialog, {
      width: '500px',
      data: { taskName: this.taskName, taskType:this.taskType ,dependsOn:this.dependsOn,input:this.input,taskAliases:this.wTaskAliases,taskTypes:this.tasks}
    });

    dialogRef.afterClosed().subscribe(result => {
      
      let task : Task ={};
      this.taskName = result.taskName;
      task.taskType = result.taskType;
      this.depends_on = result.dependsOn;
      task.input = [];
       task.input.push(result.input);
      this.wTaskAliases.push(result.taskName);
      
      task.depends_on =[];
      //console.log(this.task.depends_on[0]);
      this.updateNodes(this.taskName);

      if(this.depends_on!=undefined){
        //console.log(this.depends_on.length);
       let len2 = this.depends_on.length;
       for(let i=0;i<len2;i++){
         if(JSON.parse(JSON.stringify(result.dependsOn[i])).value!=null)
        {//console.log(JSON.parse(JSON.stringify(result.dependsOn[i])).value);
        task.depends_on.push(JSON.parse(JSON.stringify(result.dependsOn[i])).value);}
         else 
        {//console.log(JSON.stringify(result.dependsOn[i]).replace(/\"/g,'')); 
          task.depends_on.push(JSON.stringify(result.dependsOn[i]).replace(/\"/g,''));}
         }
        //console.log(this.task.depends_on);
        this.updateLinks(task.depends_on,this.taskName);
         
      }

      //console.log(this.task.depends_on);
      console.log(this.taskName)
      this.map.set(this.taskName,task);
      this.workflow.tasks = this.map;
      console.log(this.map);
      //console.log(this.map.get(this.taskName).depends_on);

      //console.log(result);
      //console.log(this.task);
      //console.log(this.wTaskAliases);
    });
  }

//calling the function for saving the data about workflow to persistence database
    saveWorkflow(): void {
      
      //owner is hardcoded
      this.workflow.owner = "chutiya";
      this.persistence.sendWorkFlow2(this.workflow.workFlowName,
                                      this.workflow.owner,this.status,this.map);
                                      console.log(this.map);    
    }

    openWnameDialog(): void {
    let dialogRef = this.dialog.open(WnameOverviewDialog, {
      width: '300px',
      data: { Wname: this.Wname }
    });

    dialogRef.afterClosed().subscribe(result => {
      
      this.workflow.workFlowName=result;
      console.log(this.workflow.workFlowName,'The dialog was closed');
      
      
    });
  }
  openjsoneditor(): void {
    let json =this.map.toJSON();
    let dialogRef = this.dialog.open(JsonEditor, {
     data: { json: json },
     width: '700px',
     height: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      let myMap = new TSMap<String,Task>();
       myMap.fromJSON(result);
       console.log(myMap);
       this.map=myMap;
       //this.workflow.tasks =this.map;
       //let len = this.map.length;
       //aliases = this.map.keys;
       this.wTaskAliases =this.map.keys();
       //console.log(len);
       this.workflowToGraph(this.map);

      
    });
  }
  openSettings(): void {
   
    let dialogRef = this.dialog.open(SettingsDialog, {
     data: { orientations: this.orientations,orientation :this.orientation ,colors:this.colorSets1,color:this.selectedColorScheme,curveTypes : this.interpolationTypes,curveType :this.curveType},
     width: '300px',
    });

    dialogRef.afterClosed().subscribe(result => {
       
                this.orientation = result.orientation;
                this.setColorScheme(result.color);
                this.setInterpolationType(result.curveType);
    });
  }

 

}

@Component({
  selector: 'dialog-overview-dialog',
  templateUrl: 'dialog-overview-dialog.html',
  styleUrls : ['dialog-overview-dialog.css']
})
export class DialogOverviewDialog {
  
  
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { } 

    
  onNoClick(): void { 

   if (this.data.taskName == null||this.data.taskType == null)
       alert("Please give task name and type");

   if (this.data.taskName != null&&this.data.taskType != null)  
    this.dialogRef.close(this.data);
  }
 onCancelClick(): void{
    this.dialogRef.close();

 }
}


@Component({
  selector: 'wname-overview-dialog',
  templateUrl: 'wname-overview-dialog.html',
  styleUrls : ['wname-overview-dialog.css']
})
export class WnameOverviewDialog {
  
  constructor(
    public dialogRef: MatDialogRef<WnameOverviewDialog >,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
   
  onNoClick(): void { 

    if (this.data.Wname == null)
       alert("Please give a name to the workflow");
    if (this.data.Wname != null)
      this.dialogRef.close(this.data.Wname);
  }
  


}

@Component({
  selector: 'jsoneditor-overview-dialog',
  templateUrl: 'jsoneditor-overview-dialog.html',
  styleUrls : ['jsoneditor-overview-dialog.css']
})
export class JsonEditor {
  
  public editorOptions: JsonEditorOptions;
  public jsondata: any;
  @ViewChild(JsonEditorComponent) editor: JsonEditorComponent;
  constructor(
    public dialogRef: MatDialogRef<JsonEditor >,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.editorOptions = new JsonEditorOptions()
    this.editorOptions.modes = ['code', 'text', 'tree', 'view']; 
    this.jsondata = {"products":[{"name":"car","product":[{"name":"honda","model":[{"id":"civic","name":"civic"},{"id":"accord","name":"accord"},{"id":"crv","name":"crv"},{"id":"pilot","name":"pilot"},{"id":"odyssey","name":"odyssey"}]}]}]};
 
   
  }

  onNoClick(): void { 

      console.log(this.editor.get())
      this.dialogRef.close(this.editor.get());
  }
  


}
@Component({
  selector: 'settings-overview-dialog',
  templateUrl: 'settings-overview-dialog.html',
  styleUrls : ['settings-overview-dialog.css']
})
export class SettingsDialog {
  
  
  
  constructor(
    public dialogRef: MatDialogRef<SettingsDialog >,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void { 

      
      this.dialogRef.close(this.data);
  }
  


}