function b1click(){
c1++;
document.getElementById("c1").innerHTML = "Clicks:"+c1;
}
function b2click(){
c2++;
document.getElementById("c2").innerHTML = "Clicks:"+c2;
}
function b3click(){
c3++;
document.getElementById("c3").innerHTML = "Clicks:"+c3;
}
function init(){
document.getElementById("bear1").onclick= b1click;
document.getElementById("bear2").onclick= b2click;
document.getElementById("bear3").onclick= b3click;

}
window.onload= init;
var c1 = 0;
var c2 = 0;
var c3 = 0;
