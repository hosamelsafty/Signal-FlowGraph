package Traverser;
 
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
 
public class Calculate {
    private LinkedList<LinkedList<String>> adjloop;
    private LinkedList<Integer> gainloop;
    private LinkedList<LinkedList<String>> adjForword;
    private LinkedList<Integer> gainForword;
   
    private LinkedList<LinkedList<String > > nonTouch1;
    private LinkedList<LinkedList<String > > nonTouch2;
    private LinkedList<Integer> nonTouchGain;
 
    public Calculate(LinkedList<LinkedList<String>> adjloop, LinkedList<Integer> gainloop,
            LinkedList<LinkedList<String>> adjForword, LinkedList<Integer> gainForword) {
        this.adjForword = adjForword;
        this.adjloop = adjloop;
        this.gainForword = gainForword;
        this.gainloop = gainloop;
        nonTouch1=new LinkedList<>();
        nonTouch2=new LinkedList<>();
        nonTouchGain=new LinkedList<>();
    }
 
    public int calcDelta() {
        LinkedList<Integer> inloop = new LinkedList<>();
        int res = 1 - makeSum(gainloop);
        for (int i = 0; i < adjloop.size() - 1; i++) {
            for (int j = i + 1; j < adjloop.size(); j++) {
                boolean flag = true;
                for (int j2 = 0; j2 < adjloop.get(j).size(); j2++) {
                    if (adjloop.get(i).contains(adjloop.get(j).get(j2))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    nonTouch1.add(adjloop.get(i));
                    nonTouch2.add(adjloop.get(j));
                    nonTouchGain.add(gainloop.get(j) * gainloop.get(i));
                    inloop.add(gainloop.get(j) * gainloop.get(i));
                }
            }
            res += makeSum(inloop);
            inloop = new LinkedList<>();
        }
 
        return res;
    }
 
    public LinkedList<Integer> calcDeltaForword() {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<Integer> inloop = new LinkedList<>();
        for (int i = 0; i < adjForword.size(); i++) {
            for (int j = 0; j < adjloop.size(); j++) {
                boolean flag = true;
                for (int j2 = 0; j2 < adjloop.get(j).size(); j2++) {
                    if (adjForword.get(i).contains(adjloop.get(j).get(j2))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    inloop.add(gainloop.get(j));
                }
 
            }
            res.add(1 - makeSum(inloop));
            inloop = new LinkedList<>();
        }
        return res;
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
 
    private int makeSum(LinkedList<Integer> res) {
        int resault = 0;
        for (int i = 0; i < res.size(); i++) {
            resault += res.get(i);
        }
        return resault;
 
    }
 
    private double makeMul(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < l1.size(); i++) {
            res.add(l1.get(i) * l2.get(i));
 
        }
        return makeSum(res);
    }
 
    public double getresault() {
        //System.out.println(calcDeltaForword());
        //System.out.println(gainForword);
        //System.out.println(calcDelta());
        return makeMul(calcDeltaForword(), gainForword) / calcDelta();
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
        visited.add("S");
        Search forward = new Search();
        forward.depthFirst(graph, visited);
        visited = new LinkedList<>();
        visited.add("S");
        DFSCycle cycle = new DFSCycle();
        cycle.depthFirst(graph, new LinkedList<>(), visited);
        cycle.deleteDublicate(cycle.getAdjloop(), cycle.getGainloop());
        Calculate d=new Calculate( cycle.getAdjloop(), cycle.getGainloop(), forward.getAdjForword(),
                forward.getGainForword());
        System.out.println(d.getNonTouch1()+" "+d.getNonTouch2());
 
    }
 
    public LinkedList<LinkedList<String>> getNonTouch1() {
        return nonTouch1;
    }
 
    public void setNonTouch1(LinkedList<LinkedList<String>> nonTouch1) {
        this.nonTouch1 = nonTouch1;
    }
 
    public LinkedList<LinkedList<String>> getNonTouch2() {
        return nonTouch2;
    }
 
    public void setNonTouch2(LinkedList<LinkedList<String>> nonTouch2) {
        this.nonTouch2 = nonTouch2;
    }
 
    public LinkedList<Integer> getNonTouchGain() {
        return nonTouchGain;
    }
 
    public void setNonTouchGain(LinkedList<Integer> nonTouchGain) {
        this.nonTouchGain = nonTouchGain;
    }
}