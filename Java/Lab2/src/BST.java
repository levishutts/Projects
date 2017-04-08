public class BST<E extends Comparable<E>> {
	
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
    	if(root == null){
    		root = new Node(data);
    	}
    	else{
    	Node<E> current = root;
    	Node<E> parent = current;
    	String child = null;
    	while (current != null){
    		parent = current;
    		if (data.compareTo(current.getData()) < 0){
    			current = current.getLeftChild(); 
    			child = "left";
    		}
    		else if (data.compareTo(current.getData()) > 0){
    			current = current.getRightChild();
    			child = "right";
    		}
    	}	
    	Node<E> newNode = new Node<E>(data);
      	newNode.setParent(parent);
      	if(child == "left"){
      		parent.setLeftChild(newNode);
      	}
      	else if(child == "right"){
      		parent.setRightChild(newNode);
      	}
    	}
    	// Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
    }

    public Node<E> find(E data){

        // Search the tree for a node whose data field is equal to data
    	Node<E> current = root;
    	while (current != null){
    		if (data == current.getData()){
    			return current;
    		}
    		else if (data.compareTo(current.getData()) < 0){
    			current = current.getLeftChild(); 
    		}
    		else if (data.compareTo(current.getData()) > 0){
    			current = current.getRightChild();
    		}
    	}
		return null;	
    }

    private Node<E> findMin(Node<E> subroot){
    	//Find the minimun value of a subtree.
    	if(subroot.getLeftChild() != null){
    		return findMin(subroot.getLeftChild());
    	}
    	return subroot;
    }
    
    public void delete(E data){
        // Find the right node to be deleted.
    	Node<E> remove = find(data);
    	if(remove == null){
    		return;
    	}
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
    	if(remove.getLeftChild() == null && remove.getRightChild() == null){
    		if(remove == root){
    			root = null;
    		}
    		else if(remove.getParent().getLeftChild() == remove){
    			remove.getParent().setLeftChild(null);
    		}
    		else if(remove.getParent().getRightChild() == remove){
    			remove.getParent().setRightChild(null);
    		}
    	}
    	
        // If said node has two children, then replace it with its successor,
        // and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.

    	else if(remove.getLeftChild() != null && remove.getRightChild() != null){
    		Node<E> successor = findMin(remove.getRightChild());
    		remove.setData(successor.getData());
    		if(successor == remove.getRightChild()){
    			if(successor.getRightChild() == null){
    				remove.setRightChild(null);
    			}else{
    				remove.setRightChild(successor.getRightChild());
    				successor.getRightChild().setParent(remove);
    			}
    		}else{
    			if(successor.getRightChild() == null){
    				successor.getParent().setLeftChild(null);
    			}else{
    				successor.getRightChild().setParent(successor.getParent());
    				successor.getParent().setLeftChild(successor.getRightChild());
    			}
    		}
    	}	    
        // If said node has one child, delete it by making its parent point to its child.
    	else if(remove.getRightChild() == null){
    		if(remove == root){
    			if(remove.getLeftChild() == null){
    				root = remove.getRightChild();
    				remove.setRightChild(null);
    			}
    			if(remove.getRightChild() == null){
    				root = remove.getLeftChild();
    				remove.setLeftChild(null);
    			}
    		}
    		else if(remove.getParent().getLeftChild() == remove){
    			remove.getParent().setLeftChild(remove.getLeftChild());
    		}
    		else if(remove.getParent().getRightChild() == remove){
    			remove.getParent().setRightChild(remove.getLeftChild());
    		}
    	}
    	else if(remove.getLeftChild() == null){
    		if(remove == remove.getParent().getLeftChild()){
    			remove.getParent().setLeftChild(remove.getRightChild());
    		}
    		else if(remove == remove.getParent().getRightChild()){
    			remove.getParent().setRightChild(remove.getRightChild());
    		}
    	}
    	// If the value specified by delete does not exist in the tree, then the structure is left unchanged.
        // Hint: You may want to implement a findMin() method to find the successor when there are two children
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    // Your Code here 
                	if(top !=  null) {
                		System.out.printf("%d ",top.getData());                  		
                		traverse("preorder", top.getLeftChild()); 
                		traverse("preorder", top.getRightChild());     
                	}  
          
                    break;
                case "inorder":
                    // Your Code here
                	if(top !=  null) {       		
                		traverse("inorder", top.getLeftChild()); 
                		System.out.printf("%d ",top.getData());                   		
                		traverse("inorder", top.getRightChild());     
                	}  
                    break;
                case "postorder":
                    // Your Code here
                	if(top !=  null) {             		
                		traverse("postorder", top.getLeftChild()); 
                		traverse("postorder", top.getRightChild()); 
                		System.out.printf("%d ",top.getData());    
                	}  
                    break;
                    
            }
        }
    }
}
