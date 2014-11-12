/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class Driver {
	
	public static Team team;
	
	public static void main(String [ ] args)
	{
		// Initialize team
		team = new Team("The Megamen", 5);
		
		// Add players and data to the team
		team.addPlayer("Bob", 0.750, "Catcher");
		team.addPlayer("George", 0.687, "Pitcher");
		team.addPlayer("Rock", 0.301, "Short Stop");
		team.addPlayer("Roll", 0.004, "Left Fielder");
		team.addPlayer("Napalm", 1.000, "Right Fielder");
		
		// Print team name (and number of players)
		System.out.println(team);
		
		//Print all players, including name, batting average, and position
		for(int i = 0; i < team.players.length; i++){
			System.out.println(team.players[i]);
		}
		
		//Calculate and print the teams batting average
		System.out.println(team.getTeamAverage());

	}
}
