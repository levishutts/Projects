function formatDate() {
	
	var theDay = this.getDate() ;	
	var theYear = this.getFullYear() ;	
	var monthIndex = this.getMonth() ;	
	var theMonth ;
	
	switch (monthIndex) {
		case 0:
			theMonth = "January" ;
			break ;
		case 1:
			theMonth = "February" ;
			break ;
		case 2:
			theMonth = "March" ;
			break ;
		case 3:
			theMonth = "April" ;
			break ;
		case 4:
			theMonth = "May" ;
			break ;
		case 5:
			theMonth = "June" ;
			break ;
		case 6:
			theMonth = "July" ;
			break ;
		case 7:
			theMonth = "August" ;
			break ;
		case 8:
			theMonth = "September" ;
			break ;
		case 9:
			theMonth = "October" ;
			break ;
		case 10:
			theMonth = "November" ;
			break ;
		case 11:
			theMonth = "December" ;
			break ;
		default:
			theMonth = "no month" ;
			break ;	
	}
		
	return (theMonth + " " + theDay + ", " + theYear) ;
}

window.alert("Click to get the current date.") ;

Date.prototype.produceString = formatDate ;

myDate = new Date() ;

document.write("Today is " + myDate.produceString() + ".") ;