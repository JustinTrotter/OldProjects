/* 
*    Name:  Justin Trotter
*    Current Date:  2/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.text.DecimalFormat;
public class Purchase {
	private String description;
	private int amount;
	
	public Purchase(){
		description = "";
		amount = 0;
	}
	
	public Purchase(String d, int a){
		description = d;
		amount = a;
	}
	
	public void setDescription(String d){
		description = d;
	}
	
	public void setAmount(int a){
		amount = a;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public String toString(){
		return "Description: " + description + "\n" + "    Amount: " + amount + "\n";
	}
}
