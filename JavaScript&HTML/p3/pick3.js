var rand1=1;
var rand2=2;
var rand3=3;

while(!(rand1==rand2&&rand2==rand3&&rand1==rand3))
{
	rand1 = randomInteger(1,10);
	rand2 = randomInteger(1,10);
	rand3 = randomInteger(1,10);
	document.write(rand1+", "+rand2+", "+rand3+"<br>");
}

document.write("<br>Match")