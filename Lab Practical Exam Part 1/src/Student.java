/* 
*    Name:  Justin Trotter
*    Current Date:  5/1/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
public class Student {
	
	private String name;
	private ArrayList<Double> tests = new ArrayList<Double>();
	private ArrayList<Double> assignments = new ArrayList<Double>();
	
	public Student(String n) {
		name = n;
	}
	
	public void addTest(double score) {
		tests.add(score);
	}
	
	public void addAssignment(double score) {
		assignments.add(score);
	}
	
	public double getAverage(ArrayList<Double> a) {
		if (a.size() == 0) return 0;
		else {
			double total = 0;
			for (int i=0; i< a.size(); i++) {
				total += a.get(i);
			}
			return total/a.size();
		}
	}
	
	public double getTestAverage() {
		return getAverage(tests);
	}
	
	public double getAssignmentAverage() {
		return getAverage(assignments);
	}
	
	public double calcOverallAverage(double testPct, double assignPct) {
		return testPct*getTestAverage() + assignPct*getAssignmentAverage();
	}
	
	public String toString() {
		return name + " has test average: " + getTestAverage() + " and assignment average: " +
	           getAssignmentAverage();
	}

}
