var num = prompt("Enter a number. Enter 'exit' to quit.");
var cubed;
while(num != "exit")
{
	cubed = num*num*num;
	document.write(num + " cubed is " + cubed + "<br>");
	num = prompt("Enter a number. Enter 'exit' to quit.");
}

document.write("<br> Thank you.")