/* 
*    Name:  Justin Trotter
*    Current Date:  3/27/2014
*    Sources Consulted: http://docs.oracle.com/javase/7/docs/api/java/lang/String.html?is-external=true
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public abstract class Staff {

	public static final int CURRENT_YEAR = 2014;
	protected int id;
	protected StaffInfo info;
	protected String position;
	protected int startYear;
	
	
	public void writeOuput(){
		System.out.println("The staff member's ID number: " + id);
		System.out.println(info);
		System.out.println("Their position in the company: " + position);
		System.out.println("The year they started at the company: " + startYear);
	}
	
	public abstract double calculatePay();
		
}