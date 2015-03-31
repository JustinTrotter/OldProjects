/*
 * Name: Justin Trotter
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

@SuppressWarnings("unused")
public class Driver {
	//VARIABLES FOR IMPORTING GRAPH MATRIX
	public static Scanner sc;
	public static Scanner userscan;
	public static int vertNum;
	
	//VARIABLES FOR TRAVERSING DFS
	public static Stack<Integer> firstEncounteredDFS;
	public static ArrayList<Integer> encounteredDFS;
	public static ArrayList<Integer> deadendsDFS;
	public static int[][] backedgesDFS;
	public static int[][] treeedgesDFS;
	public static int numCompDFS;
	
	//VARIABLES FOR TRAVERSING BFS
	public static ArrayDeque<Integer> firstEncounteredBFS;
	public static ArrayList<Integer> encounteredBFS;
	public static int[][] crossedgesBFS;
	public static int[][] treeedgesBFS;
	public static int numCompBFS;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//PROMPT USER TO CHOOSE FILE
		String fileName = null;
		while (fileName == null)
		{
		System.out.println("Please choose a file: (1, 2, or 3)");
		
		userscan = new Scanner(System.in);
		
			int choice = userscan.nextInt();
			switch(choice)
			{
			case 1:
				fileName = "sample1.txt";
				break;
			case 2:
				fileName = "sample2.txt";
				break;
			case 3:
				fileName = "sample3.txt";
				break;
			default:
				System.out.println("Error: Enter a correct integer");	
			}
		}
		System.out.println();
		//OPEN FILE
		sc = new Scanner(new File(fileName));
		
		//PARSE GRAPH FROM FILE
		int[][] graphMatrix = null;
		vertNum = 0;
		boolean isFirstIter = true;
		while (sc.hasNextLine())
		{
			//DETERMINE NUMBER OF VERTICES
			String line = sc.nextLine();
		    String[] row = line.trim().split(" ");
		    if (isFirstIter) {
		    	graphMatrix = new int[row.length][row.length];
		        isFirstIter = false;
		    }
		    //BUILD GRAPH LINE BY LINE
		    for (int i = 0; i < row.length; i++)
		    {
		    	graphMatrix[vertNum][i] = Integer.parseInt(row[i]);
		    	//System.out.println("Vertex " + vertex + ": " + i);
		    }
		    vertNum++;
		}
		//INITIALIZE EMPTY FIRST ENCOUNTED DFS STACK
		firstEncounteredDFS = new Stack<Integer>();
		
		//INITIALIZE EMPTY DEADEND DFS LIST
		deadendsDFS = new ArrayList<Integer>();
		
		//INITIALIZE BLANK BACK EDGE DFS GRAPH
		backedgesDFS = new int[vertNum][vertNum];
		
		//INITIALIZE BLANK TREE EDGE DFS GRAPH
		treeedgesDFS = new int[vertNum][vertNum];
		
		//INITIALIZE TOTAL ENCOUNTERED DFS LIST
		encounteredDFS = new ArrayList<Integer>();
		
		//INITIALIZE NUMBER OF COMPOSITIONS
		numCompDFS = 0;
		
		for (int i = 0; i < vertNum; i++)
		{
			if(!encounteredDFS.contains(i))
			{
				traverseDFS(graphMatrix, i, firstEncounteredDFS, deadendsDFS, backedgesDFS,treeedgesDFS, encounteredDFS, i);
				numCompDFS++;
			}
		}
		
		//INITIALIZE EMPTY FIRST ENCOUNTERED DFS QUEUE
		firstEncounteredBFS = new ArrayDeque<Integer>();
		
		//INITIALIZE BLANK TREE EDGE BFS GRAPH
		treeedgesBFS = new int[vertNum][vertNum];
		
		//INITIALIZE BLANK CROSS EDGE BFS GRAPH
		crossedgesBFS = new int[vertNum][vertNum];
		
		//INITIALIZE TOTAL ENCOUNTERED BFS LIST
		encounteredBFS = new ArrayList<Integer>();
		
		//INITIALIZE NUMBER OF COMPOSITIONS
		numCompBFS = 0;
		
		for (int i = 0; i < vertNum; i++)
		{
			if(!encounteredBFS.contains(i))
			{
				traverseBFS(graphMatrix, i, firstEncounteredBFS, crossedgesBFS, treeedgesBFS, encounteredBFS);
				numCompBFS++;
			}
		}
		
		//PRINT OUTPUT
		printOutput(graphMatrix);
	}
	
	public static void printOutput(int[][] graphMatrix)
	{
		System.out.println("Graph Matrix: ");
		for (int i = 0; i < vertNum; i++)
		{
			for (int j = 0; j < vertNum; j++)
			{
				System.out.print(graphMatrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("DFS: ");
		System.out.println();
		System.out.print("First encountered: ");
		for (Integer i: encounteredDFS)
		{
			int output = i+1;
			System.out.print(output);
			System.out.print(" ");
		}
		System.out.println();

		System.out.print("First dead-ends: ");
		for (Integer i: deadendsDFS)
		{
			int output = i+1;
			System.out.print(output);
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Number of connected components: " + numCompDFS);
		
		System.out.println();
		System.out.println("Tree edges:");
		for (int i = 0; i < vertNum; i++)
		{
			for (int j = 0; j < vertNum; j++)
			{
				System.out.print(treeedgesDFS[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Back edges: ");
		for (int i = 0; i < vertNum; i++)
		{
			for (int j = 0; j < vertNum; j++)
			{
				System.out.print(backedgesDFS[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("BFS: ");
		System.out.println();
		System.out.print("First encountered: ");
		for (Integer i: encounteredBFS)
		{
			int output = i+1;
			System.out.print(output);
			System.out.print(" ");
		}
		System.out.println();
		
		System.out.println();
		System.out.println("Number of connected components: " + numCompBFS);
		
		System.out.println();
		System.out.println("Tree edges:");
		for (int i = 0; i < vertNum; i++)
		{
			for (int j = 0; j < vertNum; j++)
			{
				System.out.print(treeedgesBFS[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Cross edges: ");
		for (int i = 0; i < vertNum; i++)
		{
			for (int j = 0; j < vertNum; j++)
			{
				System.out.print(crossedgesBFS[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void traverseDFS(int[][] gMatrix, int vertex, Stack<Integer> stack, ArrayList<Integer> deadendList, int[][] backEdgeGraph, int[][] treeEdgeGraph, ArrayList<Integer> encountered, int prev)
	{
		if (stack.isEmpty() || encountered.isEmpty())
			{
			stack.push(vertex);
			encountered.add(vertex);
			}
		for (int i = 0; i < vertNum; i++)
		{
			if (gMatrix[vertex][i] == 1)
			{
				if (!encountered.contains(i))
				{
					stack.push(i);
					encountered.add(i);
					treeEdgeGraph[vertex][i] = 1;
					traverseDFS(gMatrix, i, stack, deadendList, backEdgeGraph, treeEdgeGraph, encountered, vertex);
					stack.pop();
				}
				else
				{
					if (stack.contains(i) && i != prev)
						backEdgeGraph[vertex][i] = 1;
				}
			}
		}
		deadendList.add(vertex);
	}

	public static void traverseBFS(int[][] gMatrix, int vertex, ArrayDeque<Integer> queue, int[][] crossEedge, int[][] treeEdge,  ArrayList<Integer> encountered)
	{
		queue.add(vertex);
		encountered.add(vertex);
		while(!queue.isEmpty())
		{
			for (int i = 0; i < vertNum; i++)
			{
				if(gMatrix[queue.peek()][i] == 1)
				{
					if(encountered.contains(i) && queue.contains(i))
						crossEedge[queue.peek()][i] = 1;	
					if(!encountered.contains(i))
					{
						queue.add(i);
						encountered.add(i);
						treeEdge[queue.peek()][i] = 1;
					}
				}
			}
			queue.pop();
		}
	}
}