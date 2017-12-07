package Traverser;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Graph {
    private Map<String, LinkedHashSet<Node>> map = new HashMap();
    
    public void addEdge(String node1, String node2,int gain) {
        LinkedHashSet<Node> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        Node temp=new Node(node2,gain);
        adjacent.add(temp);
    }

    public void addNode(String node1) {
    	map.put(node1, null);
    }
   
    public boolean isConnected(String node1, String node2) {
    	LinkedHashSet<Node> adjacent = map.get(node1);
    	boolean is=false;
        if(adjacent==null) {
            return false;
        }
        for (Node node : adjacent) {       
          if(Objects.equals(node.getValue(), node2))
        	  is=true;
        }
        return is;
    }

    public LinkedList<Node> adjacentNodes(String last) {
        LinkedHashSet<Node> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList();
        }
        return new LinkedList<Node>(adjacent);
    }
}