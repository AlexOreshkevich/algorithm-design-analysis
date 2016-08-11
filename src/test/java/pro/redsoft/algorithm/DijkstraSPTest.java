package pro.redsoft.algorithm;

import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import pro.redsoft.algorithm.princeton.DijkstraSP;
import pro.redsoft.algorithm.princeton.DirectedEdge;
import pro.redsoft.algorithm.princeton.EdgeWeightedDigraph;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Alex Oreshkevich
 */
public class DijkstraSPTest {

    @Test
    public void testSimpleGraph() throws Exception {

        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(8, readFromFile("test5_1.txt"));

        // compute shortest paths
        int s = 0; // source vertex
        DijkstraSP sp = new DijkstraSP(graph, s);

        // print shortest path
        for (int t = 0; t < graph.V(); t++) {
            if (sp.hasPathTo(t)) {
                int dist = (int) sp.distTo(t);
                System.out.printf("%d   %d   ", t + 1, dist);
                for (DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + "  ");
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d         no path\n", s + 1, t + 1);
            }
        }


        assertEquals(4., sp.distTo(5)); //  1 to 6   4
        assertEquals("Shortest path 1 -> 7 should be 3", 3., sp.distTo(6)); //  1 to 7   3
        assertEquals(2., sp.distTo(2)); //  1 to 3   2
    }

    @Test
    public void testAssignmentInput() throws Exception {

        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(200, readFromFile("assignment5.txt"));

        // compute shortest paths
        int s = 0; // source vertex
        DijkstraSP sp = new DijkstraSP(graph, s);

        List<Integer> targetVertices = Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);
        for (Integer item : targetVertices) {
            System.out.print((int) sp.distTo(item - 1));
            System.out.print(",");
        }
    }

    private InputStream readFromFile(String fileName) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
    }
}
