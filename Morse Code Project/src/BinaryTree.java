/* 
*    Name:  Justin Trotter
*    Current Date:  3/11/2014
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
		
		Node(String newLetter, String newmorse){
			left = null;
			right = null;
			letter = newLetter;
		}
	}
	
	public BinaryTree(){
		root = null;
	}
	
	public String lookup(String morse){
		int index = 0;
		
		if(index < morse.length()){
			if(morse.charAt(index) == '.'){
				index++;
				return (lookup(root.left, index, morse));
			}
			
			else{
				index++;
				return (lookup(root.right, index, morse));
			}
		}
		else{
			return root.letter;
		}
	}
	
	public String lookup(Node node, int index, String morse){
		if (index < morse.length()){
			
			if(morse.charAt(index) == '.'){
				index++;
				return (lookup(node.left, index, morse));
			}
			else{
				index++;
				return (lookup(node.right, index, morse));
			}
		}
		else{
			return node.letter;
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
		printTree(node.left);
		System.out.print(node.letter);
		printTree(node.right);
	}
	
	public void printPostorder(){
		printPostorder(root);
		System.out.println();
	}
	
	public void printPreorder(){
		printPreorder(root);
		System.out.println();
	}
	
	private void printPreorder(Node node){
		if  (node == null) return;
		System.out.print(node.letter);
		printPreorder(node.left);
		printPreorder(node.right);	
	}
	
	private void printPostorder(Node node){
		if (node == null) return;
		printPostorder(node.left);
		printPostorder(node.right);
		System.out.print(node.letter);
	}	
}