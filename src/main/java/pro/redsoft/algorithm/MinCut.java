package pro.redsoft.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Run the randomized contraction algorithm for the min cut problem
 * Compute the min cut
 */
public class MinCut {

    private Map<HashSet, ArrayList> group = new HashMap<>();
    private Graph graph;
    private Map<Integer, ArrayList> adj;
    private int seed;

    public MinCut(Graph graph) {
        this.graph = graph;
        adj = graph.getAdj();
    }

    /**
     * Set random seeds
     */
    public void setSeed(int seed) {
        this.seed = seed;
    }

    /**
     * Random number generator
     */
    private int genRandomNum(int edgeSize) {
        Random random = new Random(seed);
        //System.out.println(random.nextInt(edgeSize));
        return random.nextInt(edgeSize);
    }

    private Set<Integer> getKey(int val) {
        for (Set<Integer> i : group.keySet()) {
            if (i.contains(val)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Implement edge contraction
     */
    private void mergeV(int v, int w) {
        // Check if v and w come from fused vertices
        for (Set<Integer> i : group.keySet()) {
            if (i.contains(v) && i.contains(w)) {
                return;
            }
        }

        HashSet<Integer> setA = new HashSet<Integer>();
        ArrayList<Integer> edgeA = new ArrayList<>();

        setA.add(v);
        setA.add(w);

        if (getKey(v) != null && getKey(w) == null) {
            setA.addAll(getKey(v));
            edgeA.addAll(adj.get(w));
            edgeA.addAll(group.get(getKey(v)));

            group.remove(getKey(v));
        } else if (getKey(w) != null && getKey(v) == null) {
            setA.addAll(getKey(w));
            edgeA.addAll(adj.get(v));
            edgeA.addAll(group.get(getKey(w)));

            group.remove(getKey(w));//
        } else if (getKey(v) != null && getKey(w) != null) {
            setA.addAll(getKey(v));
            setA.addAll(getKey(w));
            edgeA.addAll(group.get(getKey(v)));
            edgeA.addAll(group.get(getKey(w)));

            group.remove(getKey(v));
            group.remove(getKey(w));//
        } else {
            edgeA.addAll(adj.get(v));
            edgeA.addAll(adj.get(w));
        }
        edgeA.removeAll(setA);
        group.put(setA, edgeA);
    }

    /**
     * Implement randomized contraction algorithm
     *
     * @return cut number
     */
    public int minCut() {
        ArrayList<ArrayList<Integer>> edges = graph.getEdges();
        while ((adj.size() + group.keySet().size()) > 2) {
            // Pick a remaining edge (v, w) at random
            int randomE = genRandomNum(edges.size());
            ArrayList<Integer> randomEdge = edges.get(randomE);
            int v = randomEdge.get(0), w = randomEdge.get(1);
            // Merge v and w
            mergeV(v, w);
            // Remove picked edge from edge set and graph
            edges.remove(randomE);
            adj.remove(v);
            adj.remove(w);
        }

        int cut = 0;
        for (ArrayList list : group.values()) {
            cut = list.size();
        }
        if (group.keySet().isEmpty()) {
            for (List<Integer> i : adj.values()) {
                cut = i.size();
            }
        }
        return cut;
    }
}