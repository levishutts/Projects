var sent = prompt("Enter a sentence or phrase:");
var nospace = sent.trim();
var wordcount = nospace.split(" ");
var numwords = wordcount.length;

document.write("The number of words is "+ numwords +".")