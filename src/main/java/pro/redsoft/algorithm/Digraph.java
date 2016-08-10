package pro.redsoft.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Oriented (directional) graph.
 *
 * @author Alex Oreshkevich
 */
public class Digraph implements Graph {

    private int verticesNumber;
    private int edgesNumber = 0;
    private Map<Integer, List<Integer>> adj = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public Digraph(int verticesNumber) {
        this.verticesNumber = verticesNumber;
    }

    public Digraph(int verticesNumber, InputStream inputStream) throws IOException{
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;

                String[] values = line.trim().split(" ");
                if (values.length == 0){
                    //System.err.println("Skipping empty line " + lineCount);
                    continue;
                }
                List<String> parsedValues = new LinkedList<>();
                for (String item : values) {
                    if (item != null && !item.trim().isEmpty()) {
                        parsedValues.add(item);
                    }
                }
                if (parsedValues.isEmpty()){
                    //System.err.println("Skipping empty line " + lineCount);
                    continue;
                }

                // v -> w
                Integer v = Integer.valueOf(parsedValues.get(0));
                Integer w = Integer.valueOf(parsedValues.get(1));

                addEdge(v, w);
            }
        }

        if (verticesNumber == -1) {
            this.verticesNumber = adj.keySet().size() + 1;
        }
        else {
            this.verticesNumber = verticesNumber;
        }

        System.out.println("Initialized Digraph with " + verticesNumber + " vertices and " + edgesNumber + " edges. ");
        System.out.println(adj(1));
        System.out.println("Input file line count " + lineCount);
    }

    public Digraph(InputStream inputStream) throws IOException {
        this(-1, inputStream);
    }

    public void addEdge(Integer v, Integer w) {

        List<Integer> list = adj(v);
        list.add(0, w); // remove 0 for reversed order
        adj.put(v, list);

        edgesNumber++;
    }

    public List<Integer> adj(int v) {
        List<Integer> res = adj.get(v);
        if (res == null) {
            res = new LinkedList<>();
        }
        return res;
    }

    public Digraph reverse() {
        Digraph reversedGraph = new Digraph(verticesNumber);
        for (int v = 0; v < verticesNumber; v++) {
            List<Integer> list = adj(v);
            if (list != null) {
                for (int w : list) {
                    reversedGraph.addEdge(w, v);
                }
            }
        }
        return reversedGraph;
    }

    public int getVerticesNumber() {
        return verticesNumber;
    }

    public int getEdgesNumber() {
        return edgesNumber;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>verticesNumber</em>, followed by the number of edges
     * <em>edgesNumber</em>, followed by the <em>verticesNumber</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(verticesNumber + " vertices, " + edgesNumber + " edges " + "\n");
        for (int v = 0; v < verticesNumber; v++) {
            s.append(String.format("%d: ", v));
            List<Integer> vertices = adj.get(v);
            if (vertices != null) {
                for (int w : vertices) {
                    s.append(String.format("%d ", w));
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int V() {
        return verticesNumber;
    }
}
