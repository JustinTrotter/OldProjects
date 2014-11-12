/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 

public class Driver {
	
	public static Person[] people = new Person[7];
	
	
	public static void main(String [] args){
		
		// instantiate array of person objects
		people = new Person[4];
		
		// instantiate 4 people
		people[0] = new Person("Bob", 30, "male");
		people[1] = new Person("Rock", 27, "male");
		people[2] = new Person("Roll", 23, "female");
		people[3] = new Person("George", 25, "male");
		
		//print all data using toString method
		for(int i = 0; i < people.length; i++){
			System.out.println(people[i]);
		}
		
		// print just the ages not using toString method
		System.out.println("\nAges of People:");
		
		for(int i = 0; i < people.length; i++){
			System.out.println(people[i].age);
		}
		
		// change persons' names (1 and 4)
		people[0].setName("Napalm");
		people[3].setName("Blitz");
		
		// print new array using toString method
		
		for(int i = 0; i < people.length; i++){
			System.out.println(people[i]);
		}
	}
}
