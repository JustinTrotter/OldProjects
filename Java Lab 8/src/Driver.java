/* 
*    Name:  Justin Trotter
*    Current Date:  3/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 


public class Driver{
	
	public static void main(String [] arg){
		System.out.println("Recursion 1:");
		recur1(5);
		System.out.println();
		System.out.println("Recursion 2:");
		recur2(5);
		
	}
	
	public static void recur1(int n){
		if(n > 0){
			for(int i = 0; i < n; i++)
				System.out.print("*");
			System.out.println();	
			recur1(n - 1);
			for(int i = 0; i < n; i++)
				System.out.print("*");
			System.out.println();	
		}
	}
	
	public static void recur2(int n){
		if(n > 0){
			for(int i = n; i <= 5; i++)
				System.out.print("*");
			System.out.println();	
			recur2(n - 1);
			for(int i = n; i <= 4; i++)
				System.out.print("*");
			System.out.println();	
		}
	}
}