function validate(){
 var ssn = document.getElementById("ssninput").value.trim();
 var ssn = ssn.split("-");
 var valid = 0;
 if(ssn.length > 0){
	if (ssn[0].length == 3){
	valid++;
	console.log("3 characters in ssn[0]");
	}
	for (i=0; i<ssn[0].length; i++){
	if (isNaN(ssn[0].charAt(i)) != true){
		valid++;
		console.log(i +"character is a number");
		}
}}
 if(ssn.length > 1){
	if (ssn[1].length == 2){
	valid++;
	console.log("2 characters in ssn[1]");
	}for (i=0; i<ssn[1].length; i++){
	if (isNaN(ssn[1].charAt(i)) != true){
		valid++;
		console.log(i +"character is a number");
		}
}}
 if(ssn.length > 2){
	if (ssn[2].length == 4){
	valid++;
	console.log("4 characters in ssn[2]");
	}
 for (i=0; i<ssn[2].length; i++){
	if (isNaN(ssn[2].charAt(i)) != true){
		valid++;
		console.log(i +"character is a number");
		}
}}
if(valid == 12){
	document.getElementById("ssnvalidity").innerHTML = "Valid social security number";
}else{
	document.getElementById("ssnvalidity").innerHTML = "Invalid social security number";
 }}
 
 function init(){
 document.getElementById("enter").onclick = validate;
 }
 
 window.onload = init;
