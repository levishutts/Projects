var num = +prompt("Enter a number:");
var sqrt;
sqrt = Math.sqrt(num) * 100;
sqrt = Math.round(sqrt) / 100;
document.write("The square root of "+num+" is "+sqrt+".");