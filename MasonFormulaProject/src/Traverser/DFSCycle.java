package Traverser;
 
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
 
public class DFSCycle {
 
    private static final String START = "S";
    private static final String END = "C";
    private LinkedList<Integer> gains;
    private LinkedList<LinkedList<String>> adjloop;
    private LinkedList<Integer> gainloop;
 
    public DFSCycle() {
        gains = new LinkedList<Integer>();
        gainloop = new LinkedList<Integer>();
        adjloop = new LinkedList<LinkedList<String>>();
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
        // graph.addEdge("B", "F",2);
        // graph.addEdge("C", "A",2);
        // graph.addEdge("C", "E",2);
        // graph.addEdge("C", "F",2);
        // graph.addEdge("D", "B",2);
        // graph.addEdge("E", "C",2);
        // graph.addEdge("E", "F",2);
        // graph.addEdge("F", "B",2);
        // graph.addEdge("F", "C",2);
        // graph.addEdge("F", "E",2);
        LinkedList<String> visited = new LinkedList<>();
        visited.add(START);
        DFSCycle a = new DFSCycle();
        a.depthFirst(graph, new LinkedList<>(), visited);
        a.deleteDublicate(a.adjloop,a.gainloop);
 
    }
 
    int res = 1;
 
    public void depthFirst(Graph graph, LinkedList<String> visited, LinkedList<String> onS) {
        LinkedList<Node> nodes = graph.adjacentNodes(onS.getLast());
        // examine adjacent nodes
        for (Node node : nodes) {
            if (onS.contains(node.getValue())) {
                onS.add(node.getValue());
                gains.addLast(node.getGain());
                printPath(onS);
 
                onS.removeLast();
                gains.removeLast();
                //break;
            }
 
        }
        for (Node node : nodes) {
            if (visited.contains(node.getValue()) || onS.contains(node.getValue())){
                continue;
            }
 
            onS.addLast(node.getValue());
            gains.addLast(node.getGain());
            depthFirst(graph, visited, onS);
            onS.removeLast();
            if (!gains.isEmpty())
                gains.removeLast();
        }
    }
 
    private void printPath(LinkedList<String> visited) {
        LinkedList<String> path = new LinkedList<>();
        LinkedList<Integer> tempGain = new LinkedList<>(gains);
 
        boolean flag = false;
        for (String node : visited) {
            if (visited.getLast() == node)
                flag = true;
 
            if (flag) {
                path.addLast(node);
            } else {
                tempGain.remove();
            }
        }
 
        for (int i = 0; i < tempGain.size(); i++) {
            res *= tempGain.get(i);
        }
        gainloop.addLast(res);
        adjloop.addLast(path);
        res = 1;
    }
 
    public void printlist(LinkedList<String> tempGain) {
        for (int i = 0; i < tempGain.size(); i++) {
            System.out.print(tempGain.get(i));
        }
        System.out.println();
    }
 
    public void deleteDublicate(LinkedList<LinkedList<String>> tempGain,LinkedList<Integer> tempGain2) {
        LinkedList<LinkedList<String>> temp=new LinkedList<>();
        LinkedList<String> node=new LinkedList<String>();
        for (int i = 0; i < tempGain.size(); i++) {
            for (int j = 0; j < tempGain.get(i).size()-1; j++) {
            //  System.out.print(tempGain.get(i).get(j) + "  ");
                node.add(tempGain.get(i).get(j));
           
            //  printlist(node);
            }
             node.sort( new Comparator<String>(){
                    @Override
                        public int compare(String o1,String o2){
                            return Collator.getInstance().compare(o1,o2);
                        }
                    });
            //printlist(node);
            temp.add(node);
            node=new LinkedList<>();
            //System.out.println();
        }
       
        int shifted=0;
        for (int i = 0; i < temp.size()-1; i++) {
            for (int j = i+1; j < temp.size(); j++) {
                if(temp.get(i).equals(temp.get(j))){
 
                    adjloop.remove(j);
                    gainloop.remove(j);
                    temp.remove(j);
                //  break;
                   
                }
            }
        }
    }
 
    public LinkedList<LinkedList<String>> getAdjloop() {
        return adjloop;
    }
 
    public void setAdjloop(LinkedList<LinkedList<String>> adjloop) {
        this.adjloop = adjloop;
    }
 
    public LinkedList<Integer> getGainloop() {
        return gainloop;
    }
 
    public void setGainloop(LinkedList<Integer> gainloop) {
        this.gainloop = gainloop;
    }
   
   
   
}