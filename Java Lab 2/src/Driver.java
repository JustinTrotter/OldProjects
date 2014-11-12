/* 
*    Name:  Justin Trotter
*    Current Date:  2/6/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;


public class Driver {
	
	public static int counter;
	public static int[][] twoDArray;
	public static ArrayList<Integer> intArrayList;
	public static int[] oneDArray = {6, 3, 8, 2, 10, 4, 9, 5, 10, 1};
	
	public static void main(String [] args){
		
		twoDArray = new int[5][5];
		
		
		
		useCounter();
		reuseCounter();
		createIntArray();
		
		System.out.println(printSearchResult(binarySearch(6),6));
		System.out.println(printSearchResult(binarySearch(7),7));
	}

	private static void createIntArray() {	
		System.out.println("PART 3: ");
		int start, index, minIndex, minValue;
		
		//Selection Sort
		for(start = 0; start < oneDArray.length - 1; start++){
			minIndex = start;
			minValue = oneDArray[start];
			for (index = start + 1; index < oneDArray.length; index++){
				if(oneDArray[index] < minValue){
					minValue = oneDArray[index];
					minIndex = index;
				}
			}
			oneDArray[minIndex] = oneDArray[start];
			oneDArray[start] = minValue;
		}	
		
	}
	
	private static String printSearchResult(boolean b, int value)
	{
		if (b){return "The number: " + value + " was found in the array.";}
		else return "The number: " + value + " was NOT found in the array.";
		
	}
	
	private static boolean binarySearch(int value){
		
		//Binary Search
		int first, last, mid;
		boolean found = false;
		first = 0;
		last = oneDArray.length - 1;
		while(!found && first <= last){
			mid = (first + last) / 2;
			if(oneDArray[mid] == value){
				found = true;
			}
			else if (oneDArray[mid] > value){
				last = mid - 1;
			}
			else first = mid + 1;
				}
		if (found){
			return true;
		}
		else return false;
	}

	private static void reuseCounter() {
		System.out.println("PART 2:");
		counter = 1;
		intArrayList = new ArrayList<Integer>();
		while (counter < 15){
			intArrayList.add(counter);
			counter++;
		}
		System.out.println("Integer Array List Size: " + intArrayList.size());
		
		System.out.print("Integer Array List Data: ");
		for (int i = 0; i < intArrayList.size(); i++){
			System.out.print(intArrayList.get(i) + " ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Removing elements 2 and 5 (numbers 3 and 6)\n");
		
		intArrayList.remove(2);
		intArrayList.remove(5);
		
		System.out.println("Integer Array List Size: " + intArrayList.size());
		
		System.out.print("Integer Array List Data: ");
		for (int i = 0; i < intArrayList.size(); i++){
			System.out.print(intArrayList.get(i) + " ");
		}
		System.out.println();
		System.out.println();
	}

	private static void useCounter() {
		System.out.println("PART 1:");
		counter = 1;
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				twoDArray[j][i] = counter;
				counter ++;
			}
		}
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if (twoDArray[j][i] < 10){System.out.print(" " + twoDArray[j][i] + " ");}
				else System.out.print(twoDArray[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
