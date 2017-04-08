import java.util.Scanner;
import java.lang.Integer;

public class HW2 {

	public static void main(String[] args) {
         // Loop over the Scanner
         // Split each line into the task and the corresponding number (if one exists)
         // Depending on what the input task was, preform the corresponding function
         //      (ie) insert, delete, find, inoder, preorder, or postorder
         // Hint: Use a switch-case statement
         // Don't forget to close your Scanner!		
		BST tree = new BST();
		Scanner scanner = new Scanner(System.in);
		int iterations = scanner.nextInt();
		scanner.nextLine();
		for (int i=0; i < iterations; i++) {
			String line = scanner.next();
			switch(line) {
    	  		case "insert" :
    	  			tree.insert(scanner.nextInt());
    	  			break;
    	  		case "delete" :
    	  			tree.delete(scanner.nextInt());
    	  			break;
    	  		case "inorder" :
    	  			tree.traverse("inorder", tree.getRoot());
    	  			break;
    	  		case "preorder" :
    	  			tree.traverse("preorder", tree.getRoot());
    	  			break;
    	  		case "postorder" :
    	  			tree.traverse("postorder", tree.getRoot());
    	  			break;
    	  		default :
    	  			System.out.println("Invalid input");
    	  			break;
			}
		}
		scanner.close();
	}
}