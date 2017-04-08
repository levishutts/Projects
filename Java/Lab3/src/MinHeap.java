public class MinHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MinHeap(int s){
    	// Build the constructor
    	myArray  = (E[]) (new Comparable[s]);
    	maxSize = s;
    	length = 0;
    }

    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
        myArray = newArray;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void bubbleUp(int child){
    	int parent = (int) Math.floor(child / 2);
    	if(myArray[child].compareTo(myArray[parent]) < 0){
    		E swap = myArray[parent];
    		myArray[parent] = myArray[child];
    		myArray[child] = swap;
    		bubbleUp(parent);
    	}
    }
   
    public void bubbleDown(int index){
    	if(index * 2 < length){
    	if(myArray[index].compareTo(myArray[index * 2]) > 0){
    		E swap = myArray[index];
    		myArray[index] = myArray[index * 2];
    		myArray[index * 2] = swap;
    		bubbleDown(index);
    	}
    	}
    }
    
    public void insert(E data){
    
    	// Insert an element into your heap.
    	if (length == 0){
    		myArray[1] = data;
    		length = 1;
    	}
    	else{
    		myArray[length + 1] = data;
    		bubbleUp(length + 1);
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
    		length += 1;
    	}
    }

    public Comparable<E> findMin(){
        // return the minimum value in the heap
    	return myArray[1];
    }

    public Comparable<E> extract(){
        // remove and return the minimum value in the heap
    	Comparable<E> min = findMin();
    	myArray[1] = myArray[length];
    	myArray[length] = null;
    	bubbleDown(1);
    	return min;
    }
}