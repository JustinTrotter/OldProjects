
public class Vehicle {
	
	//private variables
	private String make;
	private String color;
	private String size;
	private int numInStock;
	
	//Constructors
	public Vehicle(){
		make = "";
		color = "";
		size = "";
		numInStock = 1;
	}
	
	public Vehicle(String m, String c, String s, int n){
		make = m;
		color = c;
		size = s;
		numInStock = n;
	}
	
	//Setters
	public void setMake(String m){
		make = m;
	}
	
	public void setColor(String c){
		color = c;
	}
	
	public void setSize(String s){
		size = s;
	}
	
	public void setNumInStock(int n){
		numInStock = n;
	}
	
	//Getters
	public String getMake(){
		return make;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getSize(){
		return size;
	}
	
	public int getNumInStock(){
		return numInStock;
	}
	
	//toStrings
	
	public String toString(){
		return "Make: " + make + "\n" +
				"Color: " + color + "\n" +
				"Size: " + size + "\n" +
				"Number in Stock: " + numInStock + "\n";
	}
	
}
