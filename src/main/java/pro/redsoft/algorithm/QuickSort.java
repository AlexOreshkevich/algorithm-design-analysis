package pro.redsoft.algorithm;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author Alex Oreshkevich
 */
public class QuickSort {

    private long count;
    private int[] array;

    private int mode = 0;

    // return index of median element
    public static int medianOfThree(int[] array) {
        return medianOfThree(array, 0, array.length - 1);
    }

    public static int medianOfThree(int[] array, int start, int end) {

        //System.out.println("medianOfThree " + start + " " + end);

        int length = end - start + 1;
        if (length < 3) {
            return start;
        }

        int middleInd;
        if (length % 2 == 0) {
            middleInd = length / 2 - 1;
        } else {
            middleInd = length / 2;
        }

        // System.out.println("Compare " + array[start] + ", " + array[middleInd] + " and " + array[end]);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(array[start], start);
        map.put(array[middleInd], middleInd);
        map.put(array[end], end);

        Iterator<Integer> valuesIt = map.values().iterator();
        valuesIt.next();

        return valuesIt.next();
    }

    public static void main(String[] args) {
        testSort();
    }

    private static void testSort() {
        int[] test1 = new int[]{3, 8, 1, 5, 2, 4, 7, 6};
        new QuickSort().sort(test1);
        print(test1);

        int[] test2 = new int[]{6, 1, 4, 2, 8, 3, 7, 5};
        new QuickSort().sort(test2);
        print(test2);
    }

    static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if (i != 0 && i % 50 == 0) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }

    public void sort(int[] array) {
        sort(array, 0);
    }

    public void sort(int[] array, int mode) {

        this.array = array;

        if (array.length < 2) {
            return;
        }

        this.mode = mode;

        partition(array, getPivotIndex(array, 0, array.length - 1));
    }

    private void partition(int[] array, int pivotIndex) {
        partition(array, pivotIndex, 0, array.length - 1);
    }

    private void partition(int[] array, int pivotIndex, int startInd, int endInd) {

        System.out.println("pivot " + pivotIndex + " [" + startInd + "][" + endInd + "]");

        int length = endInd - startInd + 1;

        // base case 1: do nothing for empty or single-item arrays
        if (length < 2) {
            return;
        }

        // save comparison count for every recursive call
        if (length > 0) {
            count += length - 1;
        }

        // just sort using single swap for 2-size array
        if (length == 2) {

            if (array[startInd] > array[endInd]) {
                swap(array, startInd, endInd);
            }

            return;
        }

        if (mode != 0) {
            swap(array, startInd, pivotIndex);
            pivotIndex = startInd;
        }

        int pivot = array[pivotIndex];
        int i = pivotIndex + 1;
        int j = i;

        for (; j < endInd + 1; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }

        int newPivotInd = i - 1;
        swap(array, pivotIndex, newPivotInd);

        // recursively sort subarrays
        if (newPivotInd - startInd > 0) {
            int leftSubarrayPivotInd = getPivotIndex(array, startInd, newPivotInd - 1);
            partition(array, leftSubarrayPivotInd, startInd, newPivotInd - 1);   // left  [startInd, i - 2]
        }

        if (endInd - newPivotInd - 1 > 0) {
            int rightSubarrayPivotInd = getPivotIndex(array, newPivotInd + 1, endInd);
            partition(array, rightSubarrayPivotInd, newPivotInd + 1, endInd);      // right [i, endInd]
        }
    }

    private int getPivotIndex(int[] array, int startInd, int endInd) {
        switch (mode) {
            case 0:
                return startInd;
            case 1:
                return endInd;
            case 2:
                return medianOfThree(array, startInd, endInd);
        }
        return -1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public long getCount() {
        return count;
    }

    public void display() {
        print(array);
    }
}
