/* 
*    Name:  Justin Trotter
*    Current Date:  3/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class StaffInfo {
	private String name;
	private String address;
	private String city;
	private String state;
	
	//Constructor
	public StaffInfo(String n, String a, String c, String s){
		name = n;
		address = a;
		city = c;
		state = s;
	}
	
	//Getters
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	//Setters
	public void setName(String n){
		name = n;
	}
	
	public void setAddress(String a){
		address = a;
	}
	
	public void setCity(String c){
		city = c;
	}
	
	public void setState(String s){
		state = s;
	}
	
	public String toString(){
		return "Name: " + name + "\n" + 
				"Address: " + address + "\n" + 
				"City: " + city + "\n" + 
				"State: " + state ;
	}
}
