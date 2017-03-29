var num1 = +prompt("What is your first number?");
var num2 = +prompt("What is your second number?");
var sum = num1 + num2;
var diff = num1 - num2;
var pro = num1 * num2;
var quo = num1 / num2;
document.write("Sum: "+sum +"<br>")
document.write("Difference: "+diff +"<br>")
document.write("Product: "+pro +"<br>")
document.write("Quotient: "+quo +"<br><br>")
var rem1 = num1 % 3;
var rem2 = num2 % 3;
if (rem1 == 0){
	document.write(num1+ " is divisible by 3.<br>");
}
else{
	document.write(num1+ " is not divisible by 3.<br>");
}
if (rem2 == 0){
	document.write(num2+ " is divisible by 3.<br>");
}
else{
	document.write(num2+ " is not divisible by 3.<br>");
}