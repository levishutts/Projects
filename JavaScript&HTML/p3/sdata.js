var name1 = prompt("Enter the first name:");
var name2 = prompt("Enter the last name:");
var classrank = prompt("Enter the class (freshmen, sophomore, junior, senior):");
var midterm = +prompt("Enter the midterm test score:");
var finalgr = +prompt("Enter the final test score:");
var grade

function Student(_firstName, _lastName, _cls, _mt, _f)
{
	 grade = (_mt + (2 * _f)) / 3;
	 document.write(_firstName + " " + _lastName + ", " + _cls + ", " + grade)
}

Student(name1, name2, classrank, midterm, finalgr);