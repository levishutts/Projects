public class Queue<E> {
	
	private Node<E> head; 
	private Node<E> tail;
	
	public Queue() {
		// We want to initialize our Queue to be empty 
		// (ie) set head and tail to be null
		head = null;
		tail = null;
	} 
	
	public void enqueue(E newData){
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail 
		// Hint: Think about what's different for the first node added to the Queue
		if(head == null){
			head = new Node<E>(newData, tail);
			tail = head;
		}
		else{
			Node<E> oldTail = tail;
			tail = new Node<E>(newData, null);
			oldTail.setNext(tail);
		}
	}
	
	public Node<E> dequeue(){
		// Return the head of the Queue 
		// Update head 
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node to hold important information 
		// Hint: Return null on a empty Queue
		if(head == null){
			return null;
		}else{
			Node<E> newHead = head.getNext();
			Node<E> deHead = head;
			head = newHead;
			return deHead;
		}
	} 
	
	public boolean isEmpty(){ 
		// Check if the Queue is empty
		if(head == null){
			return true;
		}return false;
	}
	
	public void printQueue(){
		// Loop through your queue and print each Node's data
		Node<E> nextN = head;	
		System.out.print("Queue: ");
		while(nextN.getNext() != null){
			System.out.print(nextN.getData());
			nextN = nextN.getNext();
		}	
		System.out.println(nextN.getData());
	}
}