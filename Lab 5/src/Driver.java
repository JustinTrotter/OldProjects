/* 
*    Name:  Justin Trotter
*    Current Date:  2/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
public class Driver{
	public static void main(String [] arg){
		
		Generic<Integer> integerObject = new Generic<Integer>(5);
		
		Generic<String> stringObject = new Generic<String>("Bob");
		
		Generic<Double> doubleObject = new Generic<Double>(3.34);
		
		Generic<Object> objectObject = new Generic<Object>("Steve");
		
		ArrayList<Generic> genericList = new ArrayList<Generic>();
		
		genericList.add(new Generic<Integer>(8));
		genericList.add(new Generic<String>("Dan"));
		
		for(Generic g : genericList){
			System.out.println(g);
		}
	}
}