var buttnum = 0
function handleClick(){
	document.getElementById("outputPar").innerHTML = "The button was pressed " + buttnum;
	buttnum = buttnum +1;
	buttnum = buttnum%3;
}
function init(){
	document.getElementById("swapLeft").onclick = handleClick;
}
if(buttnum = 0){
	document.getElementById("left") = function () { left.src = "../images/smile.png"};
	document.getElementById("cent") = function () { cent.src = "../images/plain.png"};
	document.getElementById("right") = function () { right.src = "../images/wink.png"};	
}
if(buttnum = 1){
	document.getElementById("left") = function () { left.src = "../images/plain.png"};
	document.getElementById("cent") = function () { cent.src = "../images/wink.png"};
	document.getElementById("right") = function () { right.src = "../images/smile.png"};	
}
if(buttnum = 2){
	document.getElementById("left") = function () { left.src = "../images/wink.png"};
	document.getElementById("cent") = function () { cent.src = "../images/smile.png"};
	document.getElementById("right") = function () { right.src = "../images/plain.png"};	
}

window.onload = init;