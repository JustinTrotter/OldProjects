/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
public class Driver {
	
	public static Team team1;
	public static Team team2;
	public static Team team3;
	public static Team team4;
	
	public static ArrayList<Team> teamList;
	public static ArrayList<Player> playerList;
	
	public static String commissioner;
	
	public static Scanner scanner;
	
	public static void main(String [ ] args)
	{
		scanner = new Scanner(System.in);
		// Initialize teams
		team1 = new Team("The Megamen", 5);
		team2 = new Team("The Bosses", 6);
		team3 = new Team("The Breaking Bads", 6);
		team4 = new Team("The God-Awfuls", 5);
		
		// Add players and data to the teams
		
		//The Megamen
		team1.addPlayer("Bob", 0.750, "Catcher");
		team1.addPlayer("George", 0.687, "Pitcher");
		team1.addPlayer("Rock", 0.301, "Short Stop");
		team1.addPlayer("Roll", 0.004, "Left Fielder");
		team1.addPlayer("Napalm", 1.000, "Right Fielder");
		
		//The Bosses
		team2.addPlayer("Cutman", 0.744, "Catcher");
		team2.addPlayer("Bombman", 0.434, "Pitcher");
		team2.addPlayer("Fireman", 0.674, "3rd Baseman");
		team2.addPlayer("Elecman", 0.326, "1st Baseman");
		team2.addPlayer("Gutsman", 0.946, "Center Fielder");
		team2.addPlayer("Iceman", 0.045, "Short Stop");
		
		//The Breaking Bads
		team3.addPlayer("Walter", 0.970, "Pitcher");
		team3.addPlayer("Jessie", 0.930, "Catcher");
		team3.addPlayer("Skylar", 0.234, "1st Baseman");
		team3.addPlayer("Hank", 0.783, "Left Fielder");
		team3.addPlayer("Gomez", 0.574, "Right Fielder");
		team3.addPlayer("Gus", 0.849, "3rd Baseman");
		
		//The God-Awfuls
		team4.addPlayer("Moe", 0.193, "Pitcher");
		team4.addPlayer("Larry", 0.234, "Catcher");
		team4.addPlayer("Curly", 0.299, "1st Baseman");
		team4.addPlayer("Shemp", 0.032, "Left Fielder");
		team4.addPlayer("Annie", 0.134, "Right Fielder");
		
		//Add Each Team to Array List
		teamList = new ArrayList<Team>();
		teamList.add(team1);
		teamList.add(team2);
		teamList.add(team3);
		teamList.add(team4);
		
		team1.sortTeam();
		team2.sortTeam();
		team3.sortTeam();
		team4.sortTeam();
		
		//Greet the commissioner
		System.out.println("What is your name, Commissioner?");
		commissioner = scanner.nextLine();
		System.out.println("Hello, Commissioner " + commissioner);
		
		//Print all players, including name, batting average, and position
		for(int i = 0; i < teamList.size(); i ++){
			System.out.println("***********************");
			System.out.println(teamList.get(i));
			for(int j = 0; j < teamList.get(i).players.length; j++){
				System.out.println(teamList.get(i).players[j]);
			}
			System.out.println("***********************");
			System.out.println(); 
		}
		
		//Copy all players into one arraylist
		playerList = new ArrayList<Player>();
		for(int i = 0; i < teamList.size(); i ++){
			for(int j = 0; j < teamList.get(i).players.length; j++){
				//playerList.add(teamList.get(i).players[j]);
				playerList.add(teamList.get(i).players[j]);
			}
		}
		
//		//Sort player list
		//Selection Sort
		int start, index, minIndex;
		Player minValue;
		for(start = 0; start < playerList.size() - 1; start++){
			minIndex = start;
			minValue = playerList.get(start);
			for (index = start + 1; index < playerList.size(); index++){
				if(playerList.get(index).getAverage() < minValue.getAverage()){
					minValue = playerList.get(index);
					minIndex = index;
				}
			}
			playerList.set(minIndex, playerList.get(start));
			playerList.set(start, minValue);
		}
		
//		for(int i = 0; i < playerList.size(); i++){
//			System.out.println(playerList.get(i));
//		}
		
		System.out.println("***********************");
		System.out.println("Players with Batting Average Greater than 0.300");
		for(int i = 0; i < teamList.size(); i ++){
			int count = 0;
			System.out.println("***********************");
			System.out.println(teamList.get(i));
			for(int j = 0; j < teamList.get(i).players.length; j++){
				if (teamList.get(i).players[j].getAverage() > 0.300){
					System.out.println(teamList.get(i).players[j]);
					count++;
				}
			}
			if (count == 0)
			{
				System.out.println("This team sucks too much to be on this list.");
			}
			System.out.println("***********************");
			System.out.println(); 
		}
		
		//Calculate and Print each teams batting average
		double teamAverage;
		NumberFormat formatter = new DecimalFormat("0.000");
		for(int i = 0; i < teamList.size(); i ++){
			teamAverage = 0;
			System.out.println("***********************");
			System.out.println(teamList.get(i));
			for(int j = 0; j < teamList.get(i).players.length; j++){
				teamAverage += teamList.get(i).players[j].getAverage();
			}
			teamAverage = teamAverage / teamList.get(i).players.length;
			System.out.println("Team Average: " + formatter.format(teamAverage));
			System.out.println("***********************");
			System.out.println(); 
		}
	}		
}

