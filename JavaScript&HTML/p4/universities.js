var unilist = [["Duke", "North Carolina"], ["Notre Dame", "Indiana"], ["Oregon", "Oregon"], ["Rutgers", "New Jersey"], ["Auburn", "Alabama"]]
var uni = prompt("Enter a university (Duke, Notre Dame, Oregon, Rutgers, Auburn):")

for(i in unilist){
	if(unilist[i][0]== uni){
		document.write(unilist[i][0] + " is located in " + unilist[i][1] + ".")
	}
}