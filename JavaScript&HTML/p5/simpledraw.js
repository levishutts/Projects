function draw(){
 var pen = document.getElementById("canvas").getContext("2d");
 pen.strokeStyle="Navy";
 pen.lineWidth = 2;
 pen.moveTo(50, 50);
 pen.lineTo(350, 50);
 pen.stroke();
 
 pen.fillStyle = "red";
 pen.fillRect(100,85,50,50);
 
 pen.fillStyle = "green";
 pen.fillRect(175,85,50,50);
 
 pen.fillStyle = "blue";
 pen.fillRect(250,85,50,50);
 
 pen.beginPath();
 pen.strokeStyle="Orange";
 pen.moveTo(50, 170);
 pen.lineTo(350, 170);
 pen.stroke();
 }
 
 function clear(){
 var canvasElement = document.getElementById("canvas");
 var drawContext = canvasElement.getContext("2d");
 drawContext.clearRect(0,0,canvasElement.width,canvasElement.height);
 }
 
function init(){
document.getElementById("draw").onclick = draw;
document.getElementById("clear").onclick = clear;

}
 window.onload = init;
