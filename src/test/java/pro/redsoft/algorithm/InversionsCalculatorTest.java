package pro.redsoft.algorithm;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

/**
 * Some tests for {@link InversionsCalculator}.
 *
 * @author Alex Oreshkevich
 * @see https://www.coursera.org/learn/algorithm-design-analysis/discussions/weeks/1/threads/KaC2MzJBEeayaAoFBq8aPw
 */
public class InversionsCalculatorTest {

    @Test
    public void simplestTest() {
        assertEquals(0, new InversionsCalculator().getInversionsCount(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testCase1() {
        assertEquals(3, new InversionsCalculator().getInversionsCount(new int[]{1, 3, 5, 2, 4, 6}));
    }

    @Test
    public void testCase2() {
        assertEquals(4, new InversionsCalculator().getInversionsCount(new int[]{1, 5, 3, 2, 4}));
    }

    @Test
    public void testCase3() {
        assertEquals(10, new InversionsCalculator().getInversionsCount(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testCase4() {
        assertEquals(5, new InversionsCalculator().getInversionsCount(new int[]{1, 6, 3, 2, 4, 5}));
    }

    @Test
    public void testCase_15() {
        assertEquals(56, new InversionsCalculator().getInversionsCount(new int[]{9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0}));
    }

    @Test
    public void testCase_100() {
        assertEquals(2372, new InversionsCalculator().getInversionsCount(new int[]{4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54}));
    }

    @Test
    public void testCaseWithExternalDataSource() throws Exception {

        File testFile = new File("/Users/neo/Projects/algorithm-design-analysis/src/main/resources/IntegerArray.txt");
        int[] testData = new int[100000];
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                testData[i++] = Integer.valueOf(line);
            }
        }

        System.out.println("Loaded " + i + " from external source...");
        long inversionsCount = new InversionsCalculator().getInversionsCount(testData);

        System.out.println("Calculated " + inversionsCount + " inversions.");
    }
}
