/* 
*    Name:  Justin Trotter
*    Current Date:  2/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 

public class Customer {
	private String name;
	private String creditCardNumber;
	
	public Customer(){
		name = "";
		creditCardNumber = "";
	}
	
	public Customer(String n, String ccn){
		name = n;
		creditCardNumber = ccn;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setCreditCardNumber(String ccn){
		creditCardNumber = ccn;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCreditCardNumber(){
		return creditCardNumber;
	}
	
	public String toString(){
		return "Customer Name: "  + name + "\n" + "Credit Card Number: " + creditCardNumber + "\n";
	}
}
