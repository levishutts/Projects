import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
public class HW2{
	static void topSorter(ArrayList<Node> nodes, Node current, Stack stack){
        current.setColor("gray");
 
        // Recur for all the vertices adjacent to this
        // vertex
        for(int i = 0; i < current.getSize(); i++){
        	if(nodes.get(current.getEdge(i) - 1).getColor() == "white"){
        		topSorter(nodes, nodes.get(current.getEdge(i) - 1), stack);
        	}
        }
        
        stack.push((nodes.indexOf(current) + 1) + " " + current.getName());
	}
    static void topSort(ArrayList<Node> nodes){
        Stack stack = new Stack();
 
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < nodes.size(); i++){
        	Node current = nodes.get(i);
            if (current.getColor() == "white"){
            		topSorter(nodes, current, stack);
            }
        }
        
        while(!stack.isEmpty()){
        	System.out.println(stack.pop());
        }
    }
	public static void main(String[] args){
		int i;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of nodes and edges: ");
		int numNodes = scanner.nextInt();
		int numEdges = scanner.nextInt();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(i = 0; i < numNodes; i++){
			String name = scanner.next();
			ArrayList<Integer> edges = new ArrayList<Integer>();
			Node<String> node = new Node<String>(name, edges);
			nodes.add(node);
		}
		
		for(i = 0; i < numEdges; i++){
			int node = scanner.nextInt();
			int node2 = scanner.nextInt();
			int weight = scanner.nextInt();
			nodes.get(node - 1).addEdge(node2);
		}
		
		topSort(nodes);
	}
}