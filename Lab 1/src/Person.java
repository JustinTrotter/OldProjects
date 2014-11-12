/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 

public class Person {
	

	//public variables
	public String name;
	public int age;
	public String gender;
	
	
	//Constructors
	public Person(){
		name = "";
		age = 0;
		gender = "";
	}
	
	public Person(String n, int a, String g){
		name = n;
		age = a;
		gender = g;
	}
	
	//Setters
	public void setName(String n){
		name = n;
	}
	
	public void setAge(int a){
		age = a;
	}
	
	public void setGender(String g){
		gender = g;
	}
	
	//Getters
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getGender(){
		return gender;
	}
	
	//toStrings
	public String toString(){
		return	"\nName: " + name +
				"\nAge: " + age +
				"\nGender: " + gender;
	}

}
