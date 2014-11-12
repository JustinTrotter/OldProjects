/* 
*    Name:  Justin Trotter
*    Current Date:  5/1/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class Driver {

	public static void main(String[] args) {
		
		Student s1 = new Student("Sandy");
		s1.addAssignment(78.0);
		s1.addAssignment(92.5);
		s1.addAssignment(87.5);
		s1.addAssignment(77.5);
		s1.addTest(69.5);
		s1.addTest(83.0);
		printStudentInfo(s1);
	    
	    Student s2 = new Student("Tommy");
		
		// the array below has an assignment score then a test score repeated
		// so 77.0 is an assignment score and 82 is a test, 95 assignment and so on
		// 5 assignment and 5 test scores
	    double[] scores = {77.0, 82, 95, 98, 83.5, 100, 76, 88.5, 91.5, 85};
		
	    for (int i=0; i <= scores.length - 1; i+=2) {
	    		s2.addAssignment(scores[i]);
	    		s2.addTest(scores[i+1]);
	    }
	    printStudentInfo(s2);

	}
	
	public static void printStudentInfo(Student s) {
		System.out.println(s);
	    double avg1 = s.calcOverallAverage(0.50, 0.50);
	    double avg2 = s.calcOverallAverage(0.60, 0.40);
	    System.out.printf("Overall average by 50/50 and 60/40: %5.2f and %5.2f\n\n",
	    		avg1, avg2);
	}

}
