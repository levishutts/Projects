var grade1 = +prompt("What was your first test score?");
var grade2 = +prompt("What was your second test score?");
var grade3 = +prompt("What was your third test score?");
var gradeavg;
var letgrade;

gradeavg = (grade1+grade2+grade3)/3

if(gradeavg < 60){letgrade = "F";}
else if(gradeavg >= 60 && gradeavg < 70){letgrade = "D";}
else if(gradeavg >= 70 && gradeavg < 80){letgrade = "C";}
else if(gradeavg >= 80 && gradeavg < 90){letgrade = "B";}
else if(gradeavg >= 90){letgrade = "A";}

document.write("Your average test score is " + gradeavg + ". Your letter grade is " + letgrade + ".")