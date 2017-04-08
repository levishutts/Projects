public class AVL<E extends Comparable<E>> {
    private Node<E> root;

    public AVL(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        boolean done = false;
        Node<E> temp = root;

        while(!done){
            if (root == null) {
                root = new Node<E>(data);
                temp = root;
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                if (temp.getLeftChild() == null){
                    temp.setLeftChild(new Node<E>(data));
                    temp.getLeftChild().setParent(temp);
                    done = true;
                }
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) <= 0){
                if (temp.getRightChild() == null){
                    temp.setRightChild(new Node<E>(data));
                    temp.getRightChild().setParent(temp);
                    done = true;
                }
                temp = temp.getRightChild();
            }
        }

        // Update the height of inserted node and subsequently its ancestors
        updateHeight(temp);
        // Set temp = first unbalanced node
        while (temp.getParent() != null && temp.isUnbalanced() == false) {
            temp = temp.getParent();
        }

        if (temp.isUnbalanced()) {
            // Right right case, perform left rotate
            if (balance(temp) < -1 && data.compareTo(temp.getRightChild().getData()) > 0) {
                leftRotate(temp);
            }
            // Right left, perform right left rotate
            else if (balance(temp) < -1 && data.compareTo(temp.getRightChild().getData()) < 0) {
                rightRotate(temp.getRightChild());
                leftRotate(temp);
            }
            // Left left, perform right rotate
            else if (balance(temp) > 1 && data.compareTo(temp.getLeftChild().getData()) < 0) {
                rightRotate(temp);
            }
            // Left right, perform left right rotate
            else if (balance(temp) > 1 && data.compareTo(temp.getLeftChild().getData()) > 0) {
                leftRotate(temp.getLeftChild());
                rightRotate(temp);
            }
        }
    }

    public Node<E> find(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        boolean done = false;
        Node<E> temp = root;


        while(!done){
            if (temp == null){
                return null;
            }
            if(temp.getData().compareTo(data) == 0){
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) < 0){
                temp = temp.getRightChild();
            }
        }
        return temp;
    }

    public void delete(E data){
    	Node<E> temp = find(data);
        Node<E> parent = null;

        if (temp.getParent() != null) {
            parent = temp.getParent();
        }

        if (temp == null){
            return;
        }
        
        Node<E> replacement = new Node(null);

        boolean isRight;
        boolean isLeft;
        boolean isRoot;

        boolean hasChildren;
        boolean hasLeft;
        boolean hasRight;
        boolean hasBoth;

        // Find out if the node to be deleted is the root or if it is a left/right child
        if (temp == root){
            isRoot = true;
            isLeft = false;
            isRight = false;
        } else if (temp == temp.getParent().getLeftChild()){
            isRoot = false;
            isLeft = true;
            isRight = false;
        } else {
            isRoot = false;
            isLeft = false;
            isRight = true;
        }

        // Find out if the node to be deleted has children
        // If it does, find out if it has a Right/Left Child or both
        if (temp.getLeftChild() == null && temp.getRightChild() == null){
            hasChildren = false;
            hasBoth = false;
            hasLeft = false;
            hasRight = false;
        } else if (temp.getLeftChild() != null && temp.getRightChild() != null){
            hasChildren = true;
            hasBoth = true;
            hasLeft = true;
            hasRight = true;
        } else if (temp.getLeftChild() == null){
            hasChildren = true;
            hasBoth = false;
            hasRight = true;
            hasLeft = false;
        } else {
            hasChildren = true;
            hasBoth = false;
            hasRight = false;
            hasLeft = true;
        }

        // Seperate cases if the node to be deleted is the root of the tree
        if (isRoot){
            // Seperate cases depending on the number of children the node to be deleted has
            if (!hasChildren){
                root = replacement;
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                    root = replacement;
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                    root = replacement;
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(null);
                root = temp.getLeftChild();
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(null);
                root = temp.getRightChild();
            }
        } else {
            // This is the case where it isn't the root node
            if (!hasChildren){
                if (isLeft){
                    temp.getParent().setLeftChild(null);
                } else if (isRight){
                    temp.getParent().setRightChild(null);
                }
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                }
                if(isLeft){
                    temp.getParent().setLeftChild(replacement);
                } else if (isRight){
                    temp.getParent().setRightChild(replacement);
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(temp.getParent());
                temp.getParent().setLeftChild(temp.getLeftChild());
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(temp.getParent());
                temp.getParent().setRightChild(temp.getRightChild());
            }
        }
        
        // Update the heights of all ancestors of the deleted node    
        updateHeight(parent);
        // Set temp to be the unbalanced node
        while (temp.getParent() != null && temp.isUnbalanced() == false) {
            temp = temp.getParent();
        }

        // Perform appropriate rotation
        while (temp.isUnbalanced()) {

            if (balance(temp) > 1 && balance(temp.getLeftChild()) >= 0) {
                rightRotate(temp);
            }

            else if (balance(temp) > 1 && balance(temp.getLeftChild()) < 0) {
                leftRotate(temp.getLeftChild());
                rightRotate(temp);
            }

            else if (balance(temp) < -1 && balance(temp.getRightChild()) <= 0) {
                leftRotate(temp);
            }

            else if (balance(temp) < -1 && balance(temp.getRightChild()) > 0) {
                rightRotate(temp.getRightChild());
                leftRotate(temp);
            }

            while (temp.getParent() != null && temp.isUnbalanced() == false) {
                temp = temp.getParent();
            }
        }
    }


    public void traverse(String order, Node<E> top) {
        // Perform a preorder traversal of the tree
        if (top != null){
            switch (order) {
                case "preorder":
                    if (top.getData() != null) {
                        System.out.print(top.getData().toString() + " ");
                        traverse("preorder", top.getLeftChild());
                        traverse("preorder", top.getRightChild());
                    }
                    break;
                case "inorder":
                    if (top.getData() != null) {
                        traverse("inorder", top.getLeftChild());
                        System.out.print(top.getData().toString() + " ");
                        traverse("inorder", top.getRightChild());                      
                    }
                    break;
                case "postorder":
                    if (top.getData() != null) {
                        traverse("postorder", top.getLeftChild());
                        traverse("postorder", top.getRightChild());
                        System.out.print(top.getData().toString() + " ");
                    }
                    break;
            }
        }
    }

    public Node<E> getMin(Node<E> top){
        boolean done = false;
        Node<E> temp = top;
        while(!done) {
            if (temp.getLeftChild() == null) {
                done = true;
            } else {
                temp = temp.getLeftChild();
            }
        }
        return temp;
    }
    
    public void rebalance(Node<E> node){
     	if(node == null){
    		return;
    	}
    	if(node.isUnbalanced()){
    		int left = 0;
    		int right = 0;
    		if(node.getLeftChild() != null){
    			left = node.getLeftChild().getHeight();
    		}
    		if(node.getRightChild() != null){
    			right = node.getRightChild().getHeight();
    		}
    		int balance = left - right;
    		if(balance == -2){
    			leftRotate(node.getRightChild());
    		}else if(balance == 2)
    			rightRotate(node.getLeftChild());
    	}
    	rebalance(node.getParent());
    }

    private void updateHeight(Node<E> node) {
    	
    	if(node == null){
    		return;
    	}else{
    		int hL = 0;
    		int hR = 0;
    		if(node.getLeftChild() != null){
    			hL = node.getLeftChild().getHeight();
    		}
    		if(node.getRightChild() != null){
    			hR = node.getRightChild().getHeight();
    		}
    		node.setHeight(Math.max(hL,  hR) + 1);
    	}
    	updateHeight(node.getParent());
	}

    public void leftRotate(Node<E> x){
        
        Node<E> y = x.getRightChild();

        if (y.getLeftChild() != null) {
            Node<E> t2 = y.getLeftChild();
            x.setRightChild(t2);
            t2.setParent(x);

        }

        if (x.getParent() == null) {
            x.setRightChild(y.getLeftChild());
            y.setLeftChild(x);
            y.setParent(x.getParent());
            
            x.setParent(y);
            root = y;
        }

        else if (x.getParent() != null) {
            x.setRightChild(y.getLeftChild());
            y.setLeftChild(x);
            y.setParent(x.getParent());
            if (x == x.getParent().getLeftChild()) {
                x.getParent().setLeftChild(y);
            }
            else {
                x.getParent().setRightChild(y);
            }
            x.setParent(y);
        }

        updateHeight(x);
        updateHeight(y);
        
    }

    public void rightRotate(Node<E> y){

        Node<E> x = y.getLeftChild();

        if (x.getRightChild() != null) {
            Node<E> t2 = x.getRightChild();
            y.setLeftChild(t2);
            t2.setParent(y);
        }
        
        if (y.getParent() == null) {
            y.setLeftChild(x.getRightChild());
            x.setRightChild(y);
            x.setParent(y.getParent());
            
            y.setParent(x);
            root = x;
        }

        else if (y.getParent() != null) {
            y.setLeftChild(x.getRightChild());
            x.setRightChild(y);
            x.setParent(y.getParent());
            if (y == y.getParent().getRightChild()) {
                y.getParent().setRightChild(x);
            }
            else {
                y.getParent().setLeftChild(x);
            }
            y.setParent(x);
        }
        updateHeight(x);
        updateHeight(y); 
    }
    
    private int balance(Node node) {
        if (node == null) {
            return 0;
        }
        else if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 0;
        }

        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            return node.getLeftChild().getHeight() - 0;
        }
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            return 0 - node.getRightChild().getHeight();
        }
        else {
            return node.getLeftChild().getHeight() - node.getRightChild().getHeight();
        }
    }
}
