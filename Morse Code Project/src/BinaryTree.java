/* 
*    Name:  Justin Trotter
*    Current Date:  3/7/2014
*    Sources Consulted: http://cslibrary.stanford.edu/110/BinaryTrees.html
*    
*    Honor morse Statement: In keeping with the honor morse policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class BinaryTree {
	
	private Node root;
	private int count;
	
	private static class Node{
		Node left;
		Node right;
		String letter;
		String morse;
		
		Node(String newLetter, String newmorse){
			left = null;
			right = null;
			letter = newLetter;
			morse = newmorse;
		}
	}
	
	public BinaryTree(){
		root = null;
	}
	
	public boolean lookup(String letter, String morse){
		return(lookup(root, letter, morse));
	}
	
	public boolean lookup(Node node, String letter, String morse){
		if(node == null){
			return false;
		}
		
		if (morse == node.morse){
			return true;
		}
		
		else if (morse == "."){
			return(lookup(node.left, letter, morse));
		}
		
		else {
			return(lookup(node.right, letter, morse));
		}
	}
	
	public void insert(String letter, String morse){
		root = insert(root, letter, morse);
		count = 0;
	}
	
	private Node insert(Node node, String letter, String morse){
		if (node == null){
			node = new Node(letter, morse);
		}
		
		else {
			if (morse.charAt(count) == '.'){
				count++;
				node.left = insert(node.left, letter, morse);
				
			}
			
			else if (morse.charAt(count) == '-') {
				count++;
				node.right = insert(node.right, letter, morse);
				
			}
		}
		
		return (node);
	}
	
	public void printTree(){
		printTree(root);
		System.out.println();
	}
	
	private void printTree(Node node){
		if (node == null) return;
		
		//left, node itself, right
		printTree(node.left);
		System.out.print(node.letter);
		printTree(node.right);
	}
	
	public void printPostorder(){
		printPostorder(root);
		System.out.println();
	}
	
	private void printPostorder(Node node){
		if (node == null) return;
		
		// first recur on both subtrees
		printPostorder(node.left);
		printPostorder(node.right);
		
		// then deal with the node
		System.out.print(node.letter);
		
	}
	
	public void printPaths(){
		String[] path = new String[1000];
		printPaths(root, path, 0);
	}
	
	private void printPaths(Node node, String[] path, int pathLen) { 
		  if (node==null) return;

		  // append this node to the path array 
		  path[pathLen] = node.letter; 
		  pathLen++;

		  // it's a leaf, so print the path that led to here 
		  if (node.left==null && node.right==null) { 
		    printArray(path, pathLen); 
		  } 
		  else { 
		  // otherwise try both subtrees 
		    printPaths(node.left, path, pathLen); 
		    printPaths(node.right, path, pathLen); 
		  } 
		}
	
	private void printArray(String[] ints, int len) { 
		  for (int i = 0; i < len; i++) { 
		   System.out.print(ints[i] + " "); 
		  } 
		  System.out.println(); 
		} 

	
	
	
	
	
	
	
	
	
	
	
	
}
