/* 
*    Name:  Justin Trotter
*    Current Date:  3/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class Salaried extends Staff {
	private static final double BASE_SALARIED_PAY = 5000.00;
	private static final double PAY_INCREASE_RATE = 100.00;
	
	//Constructor
	public Salaried(int i, StaffInfo sI, String p, int sY){
		id = i;
		info = sI;
		position = p;
		startYear = sY;
	}
	
	public double getBasePay(){
		return BASE_SALARIED_PAY;
	}
	
	@Override
	public double calculatePay() {
		return BASE_SALARIED_PAY + (CURRENT_YEAR - startYear) * PAY_INCREASE_RATE;
	} 
	
	@Override
	public void writeOuput() {
		super.writeOuput();
		System.out.println("Monthly Salary: $" + String.format("%.2f",calculatePay()));
		System.out.println();
		System.out.println("***********************");
		System.out.println();
	} 

}
