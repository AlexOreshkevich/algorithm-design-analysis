package pro.redsoft.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Graph, represented as adjacent list
 * Parallel edges allowed
 */
public class Graph {

    private Map<Integer, ArrayList> adj = new HashMap<Integer, ArrayList>();
    private int lineLength = 0;
    private ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

    Graph(String filename) {

        File file = new File(filename);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lineLength++;
            }

            int lineNumber = 1; // start from index 1
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                addEdge(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("File Not Found: " + filename);
            System.exit(1);
        }
    }

    private void addEdge(String line, int index) {
        String[] str = line.split("\\s+");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < str.length; i++) {
            list.add(Integer.parseInt(str[i]));
        }
        adj.put(index, list);
    }

    /**
     * @return the graph
     */
    public Map<Integer, ArrayList> getAdj() {
        return adj;
    }

    /**
     * @return the edge set
     */
    public ArrayList<ArrayList<Integer>> getEdges() {
        edgeSets();
        return edges;
    }

    private void edgeSets() {

        Set<Set> set = new HashSet<Set>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer i : adj.keySet()) {
            list = adj.get(i);
            for (Integer j : list) {
                Set<Integer> subset = new HashSet<Integer>();
                subset.add(i);
                subset.add(j);
                set.add(subset);
            }
        }

        ArrayList<Integer> edgeList;
        for (Set i : set) {
            edgeList = new ArrayList<>(i);
            edges.add(edgeList);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : adj.keySet()) {
            sb.append(i + ": " + adj.get(i) + '\n');
        }
        return sb.toString();
    }
}
