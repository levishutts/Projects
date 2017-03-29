var counter = 1;
var total = 0;
var odev
while(counter <= 20)
	
{
if(total % 2){odev = "odd"}
else{odev = "even"};	
document.write(total + " " + odev + "<br>");
total = counter + total;
counter = counter + 1;
}

document.write(total + " " + odev + "<br>");