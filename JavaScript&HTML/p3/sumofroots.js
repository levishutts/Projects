var num = +prompt("Enter a number");
var total = 0;
var sumofsqrt

function sumOfSquareRoots(num)
{
	while(num >= 1)
	{
	sumofsqrt = Math.sqrt(num);
	total = total + sumofsqrt;
	num = num - 1;
	}
	return total;
}
document.write("The sum of square roots for the number "+num+" is "+sumOfSquareRoots(num)+".")