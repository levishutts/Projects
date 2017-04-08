
public class ExtraCredit {
	static boolean compare(Node leftSub, Node rightSub){
	    if (leftSub == null && rightSub == null){
	        return true;
	    }
	    if ((leftSub == null && rightSub != null) || (leftSub != null && rightSub == null)){
	        return false;
	    }
	    return compare(leftSub.getLeftChild(), rightSub.getLeftChild()) && compare(leftSub.getRightChild(), rightSub.getRightChild());
	}
	public static void main(){
		BST t1 = new BST();
		BST t2 = new BST();
		
		t1.insert(40);
		t1.insert(60);
		t1.insert(20);
		t1.insert(50);
		t1.insert(30);
		t1.insert(70);
		
		t2.insert(4);
		t2.insert(6);
		t2.insert(2);
		t2.insert(5);
		t2.insert(3);
		t2.insert(7);
		
		System.out.println("Should be true: " + compare(t1.getRoot(), t2.getRoot()));
		
		t2.delete(6);
		
		System.out.println("Should be false: " + compare(t1.getRoot(), t2.getRoot()));
	}
}
