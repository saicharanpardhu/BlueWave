import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  	var handle = 0;
for(var i=6; i<67;i++){
var path = new Array();
var length = new Array();
  path[i] = document.getElementById('i'+i);
  l = path[i].getTotalLength();
  length[i] = l;
  path[i].style.strokeDasharray = l + ' ' + l; 
  path[i].style.strokeDashoffset = l;
}

var i = 6;
var hole = function() {
var draw = function() {

if ( i == 6 ) {
 var total_frames = 200;
}
if ( i == 7 ) {
 var total_frames = 200;
}
if ( i == 8 ) {
 var total_frames = 200;
}
if ( i == 9 ) {
 var total_frames = 200;
}
if ( i == 10 ) {
 var total_frames = 200;
}
if ( i == 11) {
 var total_frames = 200;
}
if ( i == 12 ) {
 var total_frames = 200;
}
if ( i == 13) {
 var total_frames = 200;
}
if ( i == 14 ) {
 var total_frames = 200;
}
if ( i == 15 ) {
 var total_frames = 200;
}
if ( i == 16 ) {
 var total_frames = 200;
}
if ( i == 17 ) {
 var total_frames = 200;
}
if ( i == 18 ) {
 var total_frames = 200;
}
if ( i == 19 ) {
 var total_frames = 200;
}
if ( i == 20 ) {
 var total_frames = 200;
}
if ( i == 21 ) {
 var total_frames = 200;
}
if ( i == 22 ) {
 var total_frames = 200;
}
if ( i == 23 ) {
 var total_frames = 200;
}
if ( i == 23 ) {
 var total_frames = 200;
}if ( i == 24 ) {
 var total_frames = 200;
}if ( i == 25) {
 var total_frames = 200;
}if ( i == 26 ) {
 var total_frames = 200;
}if ( i == 27 ) {
 var total_frames = 200;
}if ( i == 28) {
 var total_frames = 200;
}if ( i == 29) {
 var total_frames = 200;
}if ( i == 30 ) {
 var total_frames = 200;
}if ( i == 31 ) {
 var total_frames = 200;
}if ( i == 32 ) {
 var total_frames = 200;
}if ( i == 33) {
 var total_frames = 200;
}if ( i == 34 ) {
 var total_frames = 200;
}if ( i == 35 ) {
 var total_frames = 200;
}if ( i == 36 ) {
 var total_frames = 200;
}if ( i == 37 ) {
 var total_frames = 200;
}if ( i == 38) {
 var total_frames = 200;
}if ( i == 39) {
 var total_frames = 200;
}if ( i == 40) {
 var total_frames = 200;
}if ( i == 41 ) {
 var total_frames = 200;
}if ( i == 42) {
 var total_frames = 200;
}if ( i == 43 ) {
 var total_frames = 200;
}if ( i == 44) {
 var total_frames = 200;
}if ( i == 45 ) {
 var total_frames = 200;
}if ( i == 46 ) {
 var total_frames = 200;
}if ( i == 47 ) {
 var total_frames = 200;
}if ( i == 48 ) {
 var total_frames = 200;
}if ( i == 49) {
 var total_frames = 200;
}if ( i == 50 ) {
 var total_frames = 200;
}if ( i == 51 ) {
 var total_frames = 200;
}if ( i == 52 ) {
 var total_frames = 200;
}if ( i == 53 ) {
 var total_frames = 200;
}if ( i == 54 ) {
 var total_frames = 200;
}if ( i == 55 ) {
 var total_frames = 200;
}if ( i == 56) {
 var total_frames = 200;
}if ( i == 57 ) {
 var total_frames = 200;
}if ( i == 58 ) {
 var total_frames = 200;
}if ( i == 59 ) {
 var total_frames = 200;
}if ( i == 60 ) {
 var total_frames = 200;
}if ( i == 61 ) {
 var total_frames = 200;
}if ( i == 62 ) {
 var total_frames = 200;
}if ( i == 63 ) {
 var total_frames = 200;
}if ( i == 64 ) {
 var total_frames = 200;
}
if ( i == 65 ) {
 var total_frames = 200;
}if ( i == 66) {
 var total_frames = 200;
}


var current_frame = 0;


var path =  document.getElementById('i'+i);
var length = path.getTotalLength();

  path.style.strokeDasharray = length + ' ' + length; 
  path.style.strokeDashoffset = length;



var drew = function() {
   var progress = current_frame/total_frames;
   if (progress > 1 ) { 
   
     window.cancelAnimationFrame(handle);
    } else {
     current_frame++;
 
       path.style.strokeDashoffset = Math.floor(length * (1 - progress));
      
   handle = window.requestAnimationFrame(drew);
    
   }}
   drew();

 i++;


if ( i == 6 ) {
  draw();
}
if ( i == 7 ) {
 draw();
}
if ( i == 8 ) {
 draw();
}if ( i == 9 ) {
 draw();
}if ( i == 10 ) {
 draw();
}if ( i == 11 ) {
 draw();
}if ( i == 12 ) {
draw();
}if ( i == 13 ) {
 draw();
}if ( i == 14 ) {
 draw();
}if ( i == 15 ) {
 draw();
}if ( i == 16 ) {
 draw();
}if ( i == 17 ) {
 draw();
}if ( i == 18 ) {
 draw();
}if ( i == 19 ) {
 draw();
}
if ( i == 20 ) {
 draw();
}if ( i == 21 ) {
 draw();
}if ( i == 22 ) {
 draw();
}if ( i == 23 ) {
 draw();
 
}
if ( i == 24 ) {
 draw();
 
}if ( i == 25) {
 draw();
 
}
if ( i == 26 ) {
 draw();
}if ( i == 27 ) {
 draw();
}if ( i == 28) {
 draw();
}if ( i == 29) {
 draw();
}if ( i == 30 ) {
 draw();
}if ( i == 31 ) {
 draw();
}if ( i == 32 ) {
 draw();
}if ( i == 33) {
 draw();
}if ( i == 34 ) {
 draw();
}if ( i == 35 ) {
 draw();
}if ( i == 36 ) {
 draw();
}if ( i == 37 ) {
 draw();
}if ( i == 38) {
 draw();
}if ( i == 39) {
 draw();
}if ( i == 40) {
 draw();
}if ( i == 41 ) {
 draw();
}if ( i == 42) {
 draw();
}if ( i == 43 ) {
 draw();
}if ( i == 44) {
 draw();
}if ( i == 45 ) {
 draw();
}if ( i == 46 ) {
 draw();
}if ( i == 47 ) {
 draw();
}if ( i == 48 ) {
 draw();
}if ( i == 49) {
 draw();
}if ( i == 50 ) {
 draw();
}if ( i == 51 ) {
 draw();
}if ( i == 52 ) {
 draw();
}if ( i == 53 ) {
 draw();
}if ( i == 54 ) {
 draw();
}if ( i == 55 ) {
 draw();
}if ( i == 56) {
 draw();
}if ( i == 57 ) {
 draw();
}if ( i == 58 ) {
 draw();
}if ( i == 59 ) {
 draw();
}if ( i == 60 ) {
 draw();
}if ( i == 61 ) {
 draw();
}if ( i == 62 ) {
 draw();
}if ( i == 63 ) {
 draw();
}if ( i == 64 ) {
 draw();
}
if ( i == 65 ) {
 draw();
}if ( i == 66) {
 draw();
} 

};
draw();}
hole();
  }

}
