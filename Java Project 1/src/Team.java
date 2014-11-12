import java.text.DecimalFormat;
import java.text.NumberFormat;

/* 
*    Name:  Justin Trotter
*    Current Date:  1/30/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
public class Team {
	
	public String name;
	public Player[] players;
	public int size;
	
	private int count = 0;
	
	//Constructors
	public Team(){
		name = "";
		size = 10;
	}
	
	public Team(String n){
		name = n;
		size = 10;
	}
	
	public Team(String n, int s){
		name = n;
		size = s;
		
		players = new Player[s];
	}
	
	//Setters
	public void setName(String n){
		name = n;
	}
	
	public void setSize(int s){
		size = s;
	}
	
	//Getters
	public String getName(){
		return name;
	}
	
	public int getSize(){
		return size;
	}
	
	//ToString
	public String toString(){
		return "Team Name: " + name + "\nNumber of players: " + size;
	}
	
	// Custom Methods
	
	// Adds a player to the players array
	public void addPlayer(String n, double a, String p){
		players[count] = new Player(n,a,p);
		count++;
	}
	
	// Returns the battling average of the team
	public String getTeamAverage(){
		double teamAverage = 0;
		for (int i = 0; i < players.length; i++)
		{
			teamAverage = teamAverage + players[i].getAverage();
		}
		teamAverage = teamAverage / players.length;
		NumberFormat formatter = new DecimalFormat("0.000");
		return "\nTeam Batting Average:\n" + formatter.format(teamAverage);
	}
}

