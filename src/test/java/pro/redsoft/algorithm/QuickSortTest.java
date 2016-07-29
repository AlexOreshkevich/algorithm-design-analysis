package pro.redsoft.algorithm;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

/**
 * @author Alex Oreshkevich
 */
public class QuickSortTest {

    /*
        size    first   last    median

        10      25      29      21

        100     615     587     518

        1000    10297   10184   8921

         *      162085  164123  138382
     */

    private static int[] readTestData(int length, String path) throws Exception {
        File testFile = new File(path);
        int[] testData = new int[length];
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                testData[i++] = Integer.valueOf(line);
            }
        }
        return testData;
    }

    private static int[] readTestData(int length) throws Exception {
        return readTestData(length, buildPath(length));
    }

    private static String buildPath(int length) {
        return "/Users/neo/Projects/algorithm-design-analysis/src/main/resources/" + length + ".txt";
    }

    @Test
    public void testCase10() throws Exception {
        doTest(10, 25, 29, 21);
    }

    @Test
    public void testCase10onSecondAlg() throws Exception {

        int length = 10000;
        String path = "/Users/neo/Projects/algorithm-design-analysis/src/main/resources/QuickSort.txt";

        int[] testData = readTestData(length, path);

        QuickSort2.quicksort(testData);
        System.out.println(QuickSort2.comparisons);
    }

    @Test
    public void testCase100() throws Exception {
        doTest(100, 615, 587, 518);
    }

    @Test
    public void testCase1000() throws Exception {
        doTest(1000, 10297, 10184, 8921);
    }

    @Test
    public void testMedianOfThree() throws Exception {
        assertEquals(2, QuickSort.medianOfThree(new int[]{8, 2, 4, 5, 7, 1}));
        assertEquals(1, QuickSort.medianOfThree(new int[]{3, 2, 1}));
        assertEquals(3, QuickSort.medianOfThree(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        assertEquals(2, QuickSort.medianOfThree(new int[]{1, 7, 2}));
        assertEquals(1, QuickSort.medianOfThree(new int[]{1, 2, 7}));
        assertEquals(0, QuickSort.medianOfThree(new int[]{2, 7, 1}));
    }

    @Test
    @Ignore
    public void testCase10000() throws Exception {
        int length = 10000;
        String path = "/Users/neo/Projects/algorithm-design-analysis/src/main/resources/QuickSort.txt";

        int[] testData = readTestData(length, path);
        QuickSort quickSort = new QuickSort();
        quickSort.sort(testData, 1);
        //  quickSort.display();
        System.out.println();
        System.out.println("Comparison count " + quickSort.getCount());
    }

    private void doTest(int length, int arg0, int arg1, int arg2) throws Exception {

        // test for first case: using pivot as first element
        QuickSort qs1 = new QuickSort();
        qs1.sort(readTestData(length), 0);
        assertEquals(arg0, qs1.getCount());


        // test for second case case: using pivot as last element
        QuickSort qs2 = new QuickSort();
        qs2.sort(readTestData(length), 1);
        assertEquals(arg1, qs2.getCount());

        QuickSort qs3 = new QuickSort();
        qs3.sort(readTestData(length), 2);
        qs3.display();
        assertEquals(arg2, qs3.getCount());
    }
}
