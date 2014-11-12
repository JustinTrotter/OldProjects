/* 
*    Name:  Justin Trotter
*    Current Date:  3/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
public class Driver {
	
	public static void main (String [] args){
		ArrayList<Staff> staff = new ArrayList<Staff>();
		
		//Instanciate Employees
		staff.add(new Salaried(666, new StaffInfo("Charles Montgomery Burns", "1000 Mammon Ln.", "Springfield", "OR"), "CEO", 1954));
		staff.add(new Salaried(674, new StaffInfo("Waylon Smithers, Jr.", "15201 Maple Systems Road", "Springfield", "OR"), "Executive", 1986));
		staff.add(new Salaried(653, new StaffInfo("Franklin Grimes, Sr.", "19 Fish Smell Drive", "Springfield", "OR"), "Executive Vice President", 1990));
		staff.add(new Hourly(345, new StaffInfo("Homer Jay Simpson", "742 Evergreen Terrace", "Springfield", "OR"), "Sector 7-G Safety Inspector", 1979));
		staff.add(new Hourly(324, new StaffInfo("Lenny Leonard", "12th Avenue", "Springfield", "OR"), "Sector 7-G Woker", 1977));
		staff.add(new Hourly(323, new StaffInfo("Carl Carlson", "652 8th Avenue", "Springfield", "OR"), "Sector 7-G Worker", 2013));
		staff.add(new Internship(789, new StaffInfo("Mindy Simmons", "57 Walnut Street", "Springfield", "OR"), "Internship", 2014));
		
		//Output Company Name
		System.out.println("***********************");
		System.out.println();
		System.out.println("Springfield Nuclear Power Plant");
		System.out.println();
		System.out.println("***********************");
		System.out.println();
		
		//Display All Information of each Staff Member
		for(int i = 0; i < staff.size(); i++){
			staff.get(i).writeOuput();
		}
	}
}