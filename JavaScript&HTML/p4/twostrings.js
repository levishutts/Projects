function checkSubstring(strng, substrng){
	var stringlist = strng.split(" ");
	for(i in stringlist){
		if(substrng == stringlist[i]){
			return true;
		}
	}
}

var strng = prompt("Enter a string:");
var substrng = prompt("Enter another string:");

if(checkSubstring(strng, substrng)){
	document.write(substrng.italics() + " is a substring of the string " + strng.italics() + ".");
}
else{
	document.write(substrng.italics() + " is not a substring of the string " + strng.italics() + ".");
}