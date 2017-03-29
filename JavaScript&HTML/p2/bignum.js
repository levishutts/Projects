var num1 = +prompt("Enter first number");
var num2 = +prompt("Enter second number");
var num3 = +prompt("Enter third number");

function biggest_num(num1, num2, num3)
{
	if(num1 > num2 && num1 > num3)
	{
		return num1;
	}
	if(num2 > num1 && num2 > num3)
	{
		return num2;
	}
	if(num3 > num1 && num3 > num2)
	{
		return num3;
	}
	if(num1 == num2 && num2 == num3 && num1 == num3)
	{
		return num1;
	}
}

var result1 = biggest_num(num1, num2, num3);
document.write(result1 + " is the biggest number." + "<br>");
var result2 = biggest_num(13, 20, 30);
document.write(result2 + " is the biggest number." + "<br>");
var result3 = biggest_num(1, 9, 3);
document.write(result3 + " is the biggest number." + "<br>");
