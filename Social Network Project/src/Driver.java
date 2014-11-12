/* 
*    Name:  Justin Trotter
*    Current Date:  3/13/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
public class Driver {
	public static ArrayList<AdjacencyGraph> graphs;
	public static Scanner sc;
	
	public static void main(String [] args) throws FileNotFoundException{
		//Open file
		sc = new Scanner(new File("social_graphs.txt"));
		graphs = new ArrayList<AdjacencyGraph>();
		
		//Determin number of graphs
		int numGraphs = sc.nextInt();
		sc.nextLine();
		System.out.println("Processing " + numGraphs + " graph(s)\n");
	
		//For each graph
		for(int i = 0; i < numGraphs; i++){
			//Read header
			int vertexes = sc.nextInt();
			int edges = sc.nextInt();
			boolean directed = (sc.nextInt() != 0);
			boolean weighted = (sc.nextInt() != 0);
		
			//If Directed  (and unweighted) //twitter
			if(directed)
			{				
				System.out.println("Processing directed graph " + i + " with " + vertexes + " nodes and " + edges + " edges.");
				
				//graphs.add(new AdjacencyList(vertexes, edges, sc, weighted, directed)); 	
				graphs.add(new AdjacencyMatrix(vertexes, edges, sc, weighted, directed));
				
				//Traverse the graph with DFS
				ArrayList<Integer> returnedList = graphs.get(i).traverseDFS();
				
				//Print DFS
				String traversal = "";
				for (int j = 0; j < returnedList.size(); j++)
					traversal += returnedList.get(j) + " ";
					
				System.out.println("DFS traversal: " + traversal);
		
				//Find a list all people who are "popular"
				for (int j = 0; j < graphs.get(i).vertexes; j++){
					
					int following = graphs.get(i).getNumFollowing(j);
					int followedBy = graphs.get(i).getNumFollowedBy(j);
					
					float score;
					if(following != 0) score = (float)followedBy / (float)following;
					else score = -1;
					
					boolean popular; 
					if(score != -1) popular = score >= 2;
					else popular = followedBy > 2;
					
					String output = "Person " + j;
					if(popular)output += " IS popular.";
					else output += " IS NOT popular.";
					
					if(score != -1) output += " Popularity score: " + score + ".";
					
					output += " Followed by: " + followedBy + " Follows: " + following;
					
					System.out.println(output);	
				}
			}
			
			//Else Undirected (and weighted) //facebook
			else {	
				System.out.println("Processing undirected graph " + i + " with " + vertexes + " nodes and " + edges + " edges.");
				
				graphs.add(new AdjacencyList(vertexes, edges, sc, weighted, directed));
				//graphs.add(new AdjacencyMatrix(vertexes, edges, sc, weighted, directed));
				
				//Traverse the graph with BFS
				ArrayList<Integer> returnedList = graphs.get(i).traverseBFS();//as Adjacency Matrix
				
				//Print BFS
				String traversal = "";
				for (int j = 0; j < returnedList.size(); j++)
					traversal += returnedList.get(j) + " ";
					
				System.out.println("BFS traversal: " + traversal);
				
				//For each Person,
				for (int j = 0; j < graphs.get(i).vertexes; j++){
					//Prevent counting self as a friend or FoF
					boolean[] visited = new boolean[vertexes];
					visited[j] = true;
					
					//Get number of friends and a list of friends
					int numFriends = graphs.get(i).getNumFollowing(j);
					String[] friends = graphs.get(i).getFriends(j);
					
					//Add all friends to queue and find oldest friend
					Queue<Integer> queue = new ArrayDeque<Integer>();
					for(int k = 0; k < friends.length; k++)
						queue.add(Integer.parseInt(friends[k]));
					
					//Modified Version of BFS
					int fof = 0;
					while(!queue.isEmpty()){
						String rawInfo = graphs.get(i).getRawInfo(queue.peek());
						boolean empty = rawInfo == "";
						String[] info = rawInfo.split(",");		
						
						double smallest = Double.POSITIVE_INFINITY;
						
						if(!empty){
						//Find the followed with the smallest value that has not been visited
							for(int k = 0; k < info.length; k++){
								if(Double.parseDouble(info[k]) < smallest && !visited[Integer.parseInt(info[k])]){
								smallest = Double.parseDouble(info[k]);
								}
							}
							if(smallest != Double.POSITIVE_INFINITY){
								visited[(int) smallest] = true;
								fof++;
							}
						}
						if (smallest == Double.POSITIVE_INFINITY) queue.remove();	
					}
					
					//Get raw values from friends
					String rawFriendInfo = graphs.get(i).getRawInfo(j);
					String rawFriendWeight = graphs.get(i).getRawWeight(j);
					
					boolean empty = rawFriendInfo == ""|| rawFriendWeight == "";
					
					String[] friendInfo = rawFriendInfo.split(",");
					String[] friendWeight = rawFriendWeight.split(",");
					
					int heaviestWeight = -1;
					int heaviestFriend = -1;
					//Find the heaviest friend
					if(!empty){
						for(int k = 0; k < friendWeight.length; k++){
							if(Integer.parseInt(friendWeight[k]) > heaviestWeight){
								heaviestWeight = Integer.parseInt(friendWeight[k]);
								heaviestFriend = Integer.parseInt(friendInfo[k]);
							}
						}
					}
					
					String output = "";
					output += "Person " + j;
					output += " has " + numFriends + " friends";
					output += " and " + fof + " FoFs";
					if(heaviestFriend != -1)output += " oldest friend is " + heaviestFriend;
					if(heaviestWeight != -1)output += " (" + heaviestWeight + " days)";
					
					System.out.println(output);
					//List p's longest friendship (using the weights)
					
				}
			}
			System.out.println();
		}
		//Close file
	}
}
