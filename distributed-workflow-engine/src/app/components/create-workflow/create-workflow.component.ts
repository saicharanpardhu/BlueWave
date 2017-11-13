import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication/authentication.service';
 import {Component, OnInit, Inject,ViewEncapsulation} from '@angular/core';
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
/**
 * @title Dialog Overview
 */ 
@Component({
  selector: 'app-create-workflow',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './create-workflow.component.html',
  styleUrls: ['./create-workflow.component.css']
})
export class CreateWorkflowComponent implements OnInit{

  public tasks: Array<String> = ["git-clone","mvn-package"];
  // workflow: Array<Map<String,String>> = [];
  map : Map<String,Task> = new Map();
  workflow = new Workflow();
  task :Task;
  Wname  :String;
  taskName :String;
  taskType : String;
  dependsOn :String[];
  input :String;
  status = "created";
  wTaskAliases : string[];
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
  curveType: string = 'Linear';
  curve: any = shape.curveLinear;
  interpolationTypes = [
    'Bundle', 'Cardinal', 'Catmull Rom', 'Linear', 'Monotone X',
    'Monotone Y', 'Natural', 'Step', 'Step After', 'Step Before'
  ];

  colorSets: any;
  colorScheme: any;
  schemeType: string = 'ordinal';
  selectedColorScheme: string;

  constructor(
    public dialog: MatDialog, 
    private authentication : AuthenticationService,
    private router: Router) {

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
    this.openWnameDialog()

    if (!this.fitContainer) {
      this.applyDimensions();
    }
  }
updateData() {
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
        label: "India"
      };

      this.hierarchialGraph.nodes.push(hNode);

      this.hierarchialGraph.links.push({
        source: this.hierarchialGraph.nodes[Math.floor(Math.random() * (this.hierarchialGraph.nodes.length - 1))].id,
        target: hNode.id,
        label: 'on success'
      });

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

  select(data) {
    console.log('Item clicked', data);
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
      // var taskMap = new Map();
      // taskMap.set(result.taskName, result.taskType); 
      //console.log(result.taskName, result.taskType, result,'The dialog was closed');
      //this.map.set(result.taskName, result.taskType);
      this.updateData(); 
      // this.workflow.push(taskMap);
      console.log(result);
    });
  }




    openWnameDialog(): void {
    let dialogRef = this.dialog.open(WnameOverviewDialog, {
      width: '300px',
      data: { Wname: this.Wname }
    });

    dialogRef.afterClosed().subscribe(result => {
      // var taskMap = new Map();
      // taskMap.set(result.taskName, result.taskType); 
      this.workflow.workFlowName=result.Wname;
      console.log(this.workflow.workFlowName,'The dialog was closed');
      // this.workflow.push(taskMap);
      
    });
  }

}

@Component({
  selector: 'dialog-overview-dialog',
  templateUrl: 'dialog-overview-dialog.html',
  styleUrls : ['dialog-overview-dialog.css']
})
export class DialogOverviewDialog {
  //tasks: Array<String> = ["git-clone","mvn-package"]; 
  ///form: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 

  /* this.form = new FormBuilder().group({
              chips: [['chip'], []]
  });*/

    } 

    onItemAdded(item){
      console.log("SELECTED ITEM", item.value);
      this.data.dependsOn.push(item.value);
    }
  onNoClick(): void { 
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
    this.dialogRef.close();
  }
  


}