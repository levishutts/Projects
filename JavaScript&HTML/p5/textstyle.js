function verdana(){
var x = document.getElementsByTagName("p");

for(i=0; i< x.length; i++){
x[i].style.fontFamily = "verdana";
}
}

function times(){
var x = document.getElementsByTagName("p");
for (i=0; i< x.length; i++){
x[i].style.fontFamily = "Times New Roman";
}
}
function comic(){
var x = document.getElementsByTagName("p");
for (i=0; i< x.length; i++){
x[i].style.fontFamily = "Comic Sans MS";
}
}

function red(){
var x = document.getElementsByTagName("p");
for (i=0; i< x.length; i++){
x[i].style.color = "red";
}
}

function green(){
var x = document.getElementsByTagName("p");
for (i=0; i< x.length; i++){
x[i].style.color = "green";
}}

function black(){
var x = document.getElementsByTagName("p");
for (i=0; i< x.length; i++){
x[i].style.color = "black";
}
}
function init(){
document.getElementById("verdana").onclick= verdana;
document.getElementById("times").onclick= times;
document.getElementById("comic").onclick= comic;
document.getElementById("red").onclick= red;
document.getElementById("green").onclick= green;
document.getElementById("black").onclick= black;
}

window.onload = init;

