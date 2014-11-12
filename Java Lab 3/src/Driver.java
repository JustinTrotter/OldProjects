/* 
*    Name:  Justin Trotter
*    Current Date:  2/13/2014
*    Sources Consulted: http://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Driver {
	private static ArrayList<String> stringList;
	private static String str;
	private static StringTokenizer st;
	public static void main(String [] args){
		stringList = new ArrayList<String>();
		str = "	The * quick * brown * fox * kicked * the * lazy * dog * and * ate * his * food";
		st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			if(st.nextToken().contains("*")){
				stringList.add(st.nextToken());
			}
	    }
		for(String i : stringList){
			System.out.print("-"+i+"-");
		}		
	}
}