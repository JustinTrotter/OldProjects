/* 
*    Name:  Justin Trotter
*    Current Date:  2/20/2014
*    Sources Consulted: http://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{
	public static void main(String [] arg) throws FileNotFoundException{
		Scanner sc = new Scanner(new File ("Lab4data.txt"));
		ArrayList<String[]> linesOfLines = new ArrayList<String[]>();
		String[] lines = new String[5];
		for(int i = 0; i < lines.length; i++){
				lines[i] = sc.next();
				linesOfLines.add(lines[i].split(","));
			}
		sc.close();
		for(int i = 0; i < linesOfLines.size(); i++){
			System.out.println(isEqual(getLineSum(linesOfLines,lines.length,i), getLastLine(linesOfLines, i)));
		}
		
	}
	
	public static int getLineSum(ArrayList<String[]> list, int length, int index){
		int sum = 0;
		for(int i = 0; i < length; i++){
			sum += Integer.parseInt(list.get(index)[i]);
		}
		return sum;
	}
	
	public static int getLastLine(ArrayList<String[]> list, int index){
		return Integer.parseInt(list.get(index)[list.get(index).length - 1]);
	}
	
	public static boolean isEqual(int a, int b){
		if(a == b){return true;}
		else{return false;}
	}
}
