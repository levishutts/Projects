public class Stack<E> { 
	
	private Node<E> top;
	
	public Stack(){
		// We want to initialize our Stack to be empty 
		// (ie) Set top as null
		top = null;
	} 
	
	public void push(E newData){
		// We want to create a node whose data is newData and next node is top
		// Push this new node onto the stack 
		// Update top
		if(top == null){
			top = new Node<E>(newData, null);
		}else{
			Node<E> oldTop = top;
			top = new Node<E>(newData, oldTop);
		}
	}
	
	public Node<E> pop(){
		// Return the Node that currently represents the top of the stack 
		// Update top 
		// Hint: The order you implement the above 2 tasks matters, so use a
		// temporary node to hold important information 
		// Hint: Return null on a empty stack
		Node<E> oldTop = top;
		top = top.getNext();
		return oldTop;
	} 
	
	public boolean isEmpty(){
		//Check if the Stack is empty
		if(top == null){
			return true;
		}return false;
	} 
	
	public void printStack(){
		// Loop through your stack and print each Node's data
		Node<E> nextN = top;
		System.out.print("Stack: ");
		while(nextN.getNext() != null){
			System.out.print(nextN.getData());
			nextN = nextN.getNext();
		}	
		System.out.println(nextN.getData());
	}
}