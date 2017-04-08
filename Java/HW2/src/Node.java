import java.util.ArrayList;

public class Node<E> { 
	
	private String name; 
	private ArrayList<Integer> edges;
	private String color;
	
	public Node(String d, ArrayList<Integer> e){
		// Set the data and edges to the corresponding data
		name = d;
		edges = e;
		color = "white";
	}
	public void addEdge(int e){
		// Set the "edges" data field to the corresponding input
		edges.add(e);
	}
	public String getName(){
		// Return the "data" data field
		return name;
	}
	public int getEdge(int index){
		// return the "next" data field
		return edges.get(index);		
	}
	public int getSize(){
		return edges.size();
	}
	public void setColor(String c){
		color = c;
	}
	public String getColor(){
		return color;
	}
}