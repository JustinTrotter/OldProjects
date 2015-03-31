/*
 * Name: Justin Trotter
 */
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix extends AdjacencyGraph {
	private ArrayList<ArrayList<Element>> adjMatrix = new ArrayList<ArrayList<Element>>();

	private static class Element {
		private boolean followed;
		private int weight;

		private Element() {
			followed = false;
			weight = -1;
		}
	}

	public AdjacencyMatrix(int v, int e, Scanner s, boolean w, boolean d) {
		super(v, e, s, w, d);
		buildGraph();
	}

	@Override
	protected String getRawInfo(int index) {
		String returnInfo = "";
		for (int i = 0; i < vertexes; i++) {
			if (adjMatrix.get(index).get(i).followed)
				returnInfo += i + ",";
		}
		return returnInfo;
	}

	@Override
	protected String getRawWeight(int index) {
		String returnInfo = "";
		for (int i = 0; i < vertexes; i++) {
			if (adjMatrix.get(index).get(i).followed)
				returnInfo += adjMatrix.get(index).get(i).weight + ",";
		}
		return returnInfo;
	}

	@Override
	protected void buildGraph() {
		// Build Matrix
		for (int row = 0; row < vertexes; row++) {
			adjMatrix.add(new ArrayList<Element>());
			for (int col = 0; col < vertexes; col++) {
				adjMatrix.get(row).add(new Element());
			}
		}
		// Insert information into matrix
		for (int i = 0; i < edges; i++) {
			int nextInt = sc.nextInt();
			int nextValue = sc.nextInt();
			int nextWeight = -1;
			if (weighted)
				nextWeight = sc.nextInt();
			adjMatrix.get(nextInt).get(nextValue).followed = true;
			adjMatrix.get(nextInt).get(nextValue).weight = nextWeight;
			// Add Symmetry if its undirected
			if (!directed) {
				adjMatrix.get(nextValue).get(nextInt).followed = true;
				adjMatrix.get(nextValue).get(nextInt).weight = nextWeight;
			}
		}
	}
}
