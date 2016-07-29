package pro.redsoft.algorithm;

import org.junit.Test;

/**
 * @author Alex Oreshkevich
 */
public class MinCutTest {

    @Test
    public void test() {
        String filename = "/Users/neo/Projects/algorithm-design-analysis/src/main/resources/MinCut.txt";
        int min = Integer.MAX_VALUE;
        int minCut;

        // Run MinCut with different random seeds
        for (int i = 1; i < 2001; i++) {
            Graph graph = new Graph(filename);
            MinCut cut = new MinCut(graph);
            cut.setSeed(i);
            minCut = cut.minCut();
            System.out.print(minCut + "...");
            if (i % 15 == 0)
                System.out.println();
            if (minCut < min) {
                min = minCut;
            }
        }

        System.out.println("Min Cut from 200 Runs: " + min);
    }
}
