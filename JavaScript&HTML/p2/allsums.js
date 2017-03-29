var counter = 1;
var total = 0;
while(counter <= 20)
	
{
document.write(total + "<br>");
total = counter + total;
counter = counter + 1;
}

document.write(total);