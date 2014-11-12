import java.text.DecimalFormat;
import java.text.NumberFormat;

/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted: Stack Overflow for Number Formatting
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 

public class Player {
	
	public String name;
	public double average = 1.000;
	public String position;
	
	//Constructor
	public Player (String n, double a, String p){
		name = n;
		average = a;
		position = p;
	}
	
	// Setters
	public void setName(String n){
		name = n;
	}
	
	public void setAverage(double a){
		average = a;
	}
	
	public void setPosition(String p){
		position = p;
	}
	
	// Getters
	public String getName(){
		return name;
	}
	
	public double getAverage(){
		return average;
	}
	
	public String getPosition(){
		return position;
	}
	
	// toString
	public String toString(){
		NumberFormat formatter = new DecimalFormat("0.000");
		return "\nName: " + name + "\nBatting Average: " + formatter.format(average) + "\nPosition: " + position;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
