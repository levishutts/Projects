function draw(){
var colors = [ "Aquamarine", "Blue", "Coral", "CornflowerBlue", "DarkOrchid",
"DarkOrange", "DeepSkyBlue", "DodgerBlue", "ForestGreen", "Gold", "Khaki", "MediumBlue", "MediumPurple", "Navy", "PaleGreen","Plum", "Silver", "SkyBlue",
"Tan", "Tomato"];
var ranx = randomInteger(0,700);
var rany = randomInteger(0,700);
var ranrad = randomInteger(10, 70);
var pen = document.getElementById("canvas").getContext("2d");
pen.beginPath();
pen.fillStyle = randomElement(colors);
pen.arc(ranx,rany,ranrad,0, 2*Math.PI);
pen.fill();

}

 function clear(){
 var canvasElement = document.getElementById("canvas");
 var drawContext = canvasElement.getContext("2d");
 drawContext.clearRect(0,0,canvasElement.width,canvasElement.height);
}
 function init(){
 document.getElementById("clear").onclick = clear;
 document.getElementById("draw").onclick = draw;
}
window.onload= init;
