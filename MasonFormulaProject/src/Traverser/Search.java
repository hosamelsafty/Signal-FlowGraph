package Traverser;
import java.util.LinkedList;

public class Search {

    private static  String START = "S";
    private static  String END = "C";
    private LinkedList<LinkedList<String>> adjForword;
    private LinkedList<Integer> gainForword;
    public Search() {
    	adjForword=new LinkedList<>();
    	gainForword=new LinkedList<>();
    }
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
        Search a=new Search();
        a.depthFirst(graph, visited);
       a.print(a.adjForword, a.gainForword);
    }
    int res=1;
    public void depthFirst(Graph graph, LinkedList<String> visited) {
        LinkedList<Node> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (Node node : nodes) {
            if (visited.contains(node.getValue())) {
                continue;
            }
            if (node.getValue().equals(END)) {
                visited.add(node.getValue());
                res*=node.getGain();
                printPath(visited);
              //  System.out.println(res);
                
                visited.removeLast();
                res/=node.getGain();
                break;
            }
        }
        for (Node node : nodes) {
            if (visited.contains(node.getValue()) || node.getValue().equals(END)) {
                continue;
            }
            
            visited.addLast(node.getValue());
            res*=node.getGain();
            depthFirst(graph, visited);
            visited.removeLast();
            res/=node.getGain();
        }
    }

    private void printPath(LinkedList<String> visited) {
        
    	adjForword.addLast(new LinkedList<String>(visited));
    	gainForword.addLast(res);
        
    }
	public LinkedList<LinkedList<String>> getAdjForword() {
		return adjForword;
	}
	public void setAdjForword(LinkedList<LinkedList<String>> adjForword) {
		this.adjForword = adjForword;
	}
	public LinkedList<Integer> getGainForword() {
		return gainForword;
	}
	public void setGainForword(LinkedList<Integer> gainForword) {
		this.gainForword = gainForword;
	}
	public void print(LinkedList<LinkedList<String>>aa,LinkedList<Integer>a){
		for (int i = 0; i < a.size(); i++) {
			System.out.println(aa.get(i)+"  "+a.get(i));
		}
		
	}
}