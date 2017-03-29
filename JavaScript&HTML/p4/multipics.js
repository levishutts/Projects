function init(){
	picObject = document.getElementById("thePicture")
	document.getElementById("scotland").onclick = function () {picObject.src = "../images/scotlandflag.png"};
	document.getElementById("southafrica").onclick = function () {picObject.src = "../images/southafricaflag.png"};
	document.getElementById("cameroon").onclick = function () {picObject.src = "../images/cameroonflag.png"};
	document.getElementById("lithuania").onclick = function () {picObject.src = "../images/lithuaniaflag.png"};
	document.getElementById("uruguay").onclick = function () {picObject.src = "../images/uruguayflag.png"};
}

window.onload = init;