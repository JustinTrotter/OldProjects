/*
 * Name: Justin Trotter
 * Current Date: 3/13/2014
 * Sources Consulted: http://docs.oracle.com/javase/7/docs/api/java/util/Vector.html
 *
 * Honor morse Statement: In keeping with the honor morse policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort.
 * ... My Signature is on File.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyList extends AdjacencyGraph {
	private ArrayList<Vertex> adjLists = new ArrayList<Vertex>();;

	private static class Neighbor {
		private int vertexNumber = -1;
		private int weight = -1;
		private Neighbor next;

		private Neighbor(int vNum, Neighbor nbr) {
			vertexNumber = vNum;
			next = nbr;
		}

		private Neighbor(int vNum, int wgt) {
			if (vertexNumber == -1) {
				vertexNumber = vNum;
				weight = wgt;
			} else
				insert(vNum, wgt);
		}

		private Neighbor(int vNum, int wgt, Neighbor nbr) {
			vertexNumber = vNum;
			weight = wgt;
			next = nbr;
		}

		private void insert(int value) {
			insert(next, value);
		}

		private void insert(int value, int wgt) {
			insert(next, value, wgt);
		}

		private void insert(Neighbor nbr, int value) {
			if (next == null) {
				next = new Neighbor(value, null);
			} else {
				next.insert(value);
			}
		}

		private void insert(Neighbor nbr, int value, int wgt) {
			if (next == null) {
				next = new Neighbor(value, wgt, null);
			} else {
				next.insert(next, value, wgt);
			}
		}

		private String getRawInfo() {
			String info = "";
			info += vertexNumber;
			if (next != null)
				info += "," + next.getRawInfo();
			return info;
		}

		private String getRawWeight() {
			String info = "";
			info += weight;
			if (next != null)
				info += "," + next.getRawWeight();
			return info;
		}
	}

	private static class Vertex {
		private Neighbor adjList;

		private Vertex() {
			adjList = null;
		}
	}

	public AdjacencyList(int v, int e, Scanner s, boolean w, boolean d) {
		super(v, e, s, w, d);
		buildGraph();
	}

	@Override
	protected String getRawInfo(int index) {
		String returnInfo = "";
		if (adjLists.get(index).adjList != null)
			returnInfo += adjLists.get(index).adjList.getRawInfo();
		return returnInfo;
	}

	@Override
	protected String getRawWeight(int index) {
		String returnInfo = "";
		if (adjLists.get(index).adjList != null)
			returnInfo += adjLists.get(index).adjList.getRawWeight();
		return returnInfo;
	}

	@Override
	protected void buildGraph() {
		// Add Vertexes
		for (int i = 0; i < vertexes; i++) {
			adjLists.add(new Vertex());
		}
		// Add Neighbors to Vertexes
		for (int i = 0; i < edges; i++) {
			int nextInt = sc.nextInt();
			int nextValue = sc.nextInt();
			int nextWeight = -1;
			if (weighted)
				nextWeight = sc.nextInt();
			if (adjLists.get(nextInt).adjList == null)
				adjLists.get(nextInt).adjList = new Neighbor(nextValue,
						nextWeight);
			else
				adjLists.get(nextInt).adjList.insert(nextValue, nextWeight);
			// Add Symmetry if its undirected
			if (!directed) {
				if (adjLists.get(nextValue).adjList == null)
					adjLists.get(nextValue).adjList = new Neighbor(nextInt,
							nextWeight);
				else
					adjLists.get(nextValue).adjList.insert(nextInt, nextWeight);
			}
		}
	}
}