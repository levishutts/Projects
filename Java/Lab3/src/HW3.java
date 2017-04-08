import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, findMin, removeMin, isEmpty, or print
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
    	pQueue<Integer> heap = new pQueue(100);
		Scanner scanner = new Scanner(System.in);
		int iterations = scanner.nextInt();
		scanner.nextLine();
		for (int i=0; i < iterations; i++) {
			String line = scanner.next();
			switch(line) {
    	  		case "insert" :
    	  			heap.insert(scanner.nextInt());
    	  			break;
    	  		case "findMin" :
    	  			System.out.println(heap.findMin());
    	  			break;
    	  		case "removeMin" :
    	  			System.out.println(heap.removeMin());
    	  			break;
    	  		case "isEmpty" :
    	  			if(heap.isEmpty()){
    	  				System.out.println("Empty");
    	  			}else{
    	  				System.out.println("Not Empty");
    	  			}
    	  			break;
    	  		case "print" :
    	  			heap.print();
    	  			break;
    	  		default :
    	  			System.out.println("Invalid input");
    	  			break;
			}
		}
		scanner.close();
	}
}