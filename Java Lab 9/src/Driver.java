/* 
*    Name:  Justin Trotter
*    Current Date:  4/4/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class Driver{
	
	private static int[] arrayInts = {2,7,3,9,10,15,4,12,20,5,17};
	
	public static void main(String [] arg){
		System.out.println("Sum: " + sum(arrayInts, 0));
		System.out.println("2: " + search(arrayInts, 2, 0));
		System.out.println("12: " + search(arrayInts, 12, 0));
		System.out.println("17: " + search(arrayInts, 17, 0));
		System.out.println("6: " + search(arrayInts, 6, 0));
	}
	
	public static int sum(int[] array, int index){
		if (index == array.length) return 0;
		else return array[index] + sum(array, index + 1);
	}
	
	public static boolean search(int[] array, int key, int start){
		if(key == array[start]) return true;
		if(start + 1 < array.length) return search(array, key, start + 1);
		return false;
	}
}

