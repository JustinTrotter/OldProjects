/*
 * Name: Justin Trotter
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class AdjacencyGraph {
	protected int vertexes;
	protected int edges;
	protected boolean weighted;
	protected boolean directed;
	protected Scanner sc;

	public AdjacencyGraph(int v, int e, Scanner s, boolean w, boolean d) {
		vertexes = v;
		edges = e;
		sc = s;
		weighted = w;
		directed = d;
	}

	protected void buildGraph() {
	}

	protected String getRawInfo(int index) {
		return null;
	}

	protected String getRawWeight(int index) {
		return null;
	}

	public final int getNumFollowing(int index) {
		String[] data = getRawInfo(index).split(",");
		if (data[0] != "")
			return data.length;
		else
			return 0;
	}

	public final String[] getFriends(int index) {
		return getRawInfo(index).split(",");
	}

	public final int getNumFollowedBy(int index) {
		int count = 0;
		// go through each vertex
		for (int i = 0; i < vertexes; i++) {
			String[] data = getRawInfo(i).split(",");
			// and their neighbors
			for (int j = 0; j < data.length; j++) {
				// if a neighbor is index, then count++
				if (data[j] != "") {
					if (Integer.parseInt(data[j]) == index)
						count++;
				}
			}
		}
		return count;
	}

	public final ArrayList<Integer> traverseBFS() {
		// initialize containers and flags
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[vertexes];
		// initalize root
		int root = 0;
		// default containers and flags with root
		queue.add(root);
		visited[root] = true;
		returnList.add(root);
		// loop through queue until its empty
		while (!queue.isEmpty()) {
			// Get each followers from the front of the queue
			String rawInfo = getRawInfo(queue.peek());
			boolean empty = rawInfo == "";
			String[] info = rawInfo.split(",");
			double smallest = Double.POSITIVE_INFINITY;
			if (!empty) {
				// Find the followed with the smallest value that has not been
				// visited
				for (int i = 0; i < info.length; i++) {
					if (Double.parseDouble(info[i]) < smallest
							&& !visited[Integer.parseInt(info[i])]) {
						smallest = Double.parseDouble(info[i]);
					}
				}
				if (smallest != Double.POSITIVE_INFINITY) {
					queue.add((int) smallest);
					visited[(int) smallest] = true;
					returnList.add((int) smallest);
				}
			}
			if (smallest == Double.POSITIVE_INFINITY)
				queue.remove();
		}
		return returnList;
	}

	public final ArrayList<Integer> traverseDFS() {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[vertexes];
		// initialize root
		int root = 0;
		// default containers and flags with root
		stack.push(root);
		visited[root] = true;
		returnList.add(root);
		// loop through stack until its empty
		while (!stack.isEmpty()) {
			// Get each followers from the top of the stack
			String rawInfo = getRawInfo(stack.peek());
			boolean empty = rawInfo == "";
			String[] info = rawInfo.split(",");
			double smallest = Double.POSITIVE_INFINITY;
			// if there are any followed
			if (!empty) {
				// Find the followed with the smallest value that has not been
				// visited
				for (int i = 0; i < info.length; i++) {
					if (Double.parseDouble(info[i]) < smallest
							&& !visited[Integer.parseInt(info[i])]) {
						smallest = Double.parseDouble(info[i]);
					}
				}
			}
			// If there is no unvisited then pop the stack and restart loop
			if (smallest == Double.POSITIVE_INFINITY)
				stack.pop();
			// Otherwise //Add smallest to the stack and mark as visited and add
			// to list
			else {
				stack.push((int) smallest);
				visited[(int) smallest] = true;
				returnList.add((int) smallest);
			}
		}
		return returnList;
	}
}