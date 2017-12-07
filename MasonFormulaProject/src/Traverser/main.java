package Traverser;

import java.util.LinkedList;

public class main {
	private static final String START = "A";
	private static final String END = "E";

	public static void main(String[] args) {

		Graph graph = new Graph();
		graph.addEdge("y1", "y2", 1);
		graph.addEdge("y2", "y3", 5);
		graph.addEdge("y2", "y6", 10);
		graph.addEdge("y3", "y4", 10);
		graph.addEdge("y4", "y3", -1); 
		graph.addEdge("y4", "y5", 2);
		graph.addEdge("y5", "y2", -1);
		graph.addEdge("y5", "y4", -2);
		graph.addEdge("y5", "y5", 1);
		graph.addEdge("y6", "y5", 2);
		graph.addEdge("y6", "y6", -1);
		
		LinkedList<String> visited = new LinkedList<>();
		visited.add(START);
		Search forward = new Search();
		forward.depthFirst(graph, visited);
		visited = new LinkedList<>();
		visited.add(START);
		DFSCycle cycle = new DFSCycle();
		cycle.depthFirst(graph, new LinkedList<>(), visited);
		cycle.deleteDublicate(cycle.getAdjloop(),cycle.getGainloop());
		LinkedList<Integer> a=cycle.getGainloop();
		System.out.println(new Calculate( cycle.getAdjloop(), cycle.getGainloop(), forward.getAdjForword(),
				forward.getGainForword()).calcDelta());

	}
}
