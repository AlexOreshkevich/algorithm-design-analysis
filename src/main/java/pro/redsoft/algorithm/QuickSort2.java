package pro.redsoft.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alex Oreshkevich
 */
public class QuickSort2 {

    // Global variable for the list being sorted makes this easier
    // Technically, I would like to the array by reference, but it doesn't seem to work properly
    public static List<Integer> list;


    // Global variable to keep track of the number of comparisons made (according to assignment spec - add
    // m-1 to comparisons for each recursive call on a list with m elements).
    public static int comparisons = 0;

    /**
     * Partitions an array according to an arbitrary pivot index
     *
     * @param list       - a List of Integers to be partitioned. The list is assumed to have length
     *                   > 0
     * @param startIndex - the start of the subset of the list to be partitioned. Assumed that 0 <
     *                   startIndex <= stopIndex
     * @param endIndex   - the end of the subset of the list to be partitioned. Assumed that
     *                   startIndex <= stopIndex < number of element in list
     * @return - the list passed as the list argument is partitioned on completion
     */
    public static int partition(List<Integer> list, int startIndex, int endIndex) {

        // Choose a pivot index
        int pivotIndex = medianOfThree(list, startIndex, endIndex);

        // Swap the first element of the array with the pivot as pre-processing step
        Collections.swap(list, pivotIndex, startIndex);

        // Start the border for elements <= partition starting at the pivotIndex + 1
        int i = startIndex + 1;

        // Walk through the array, partitioning the elements starting at the pivotIndex + 1
        for (int j = startIndex + 1; j <= endIndex; j++) {
            if (list.get(j) < list.get(startIndex)) {
                // This element should be in the partition for elements less than the pivot element
                // Swap to the border of the two partitions
                Collections.swap(list, j, i);
                i++; // move the border of the two partitions
            }
        }

        // Finally, swap the pivot and the i-1 index to finish partition
        Collections.swap(list, startIndex, i - 1);

        // Return the location of the pivot element (which is now in the correct location)
        return i - 1;
    }

    public static void quicksort(List<Integer> list) {
        quicksort(list, 0, list.size() - 1);
    }

    public static void quicksort(int[] array) {
        List<Integer> list = new LinkedList<>();
        for (int v : array) {
            list.add(v);
        }

        quicksort(list);
    }

    /**
     * Sorts arrays in ascending sorted order
     *
     * @param list       - a list of integers to be sorted. The number of items is assumed to be >
     *                   0
     * @param startIndex - the start of the subset of the list to be sorted. Assumed that 0 <
     *                   startIndex <= stopIndex
     * @param endIndex   - the end of the subset of the list to be sorted. Assumed that startIndex
     *                   <= stopIndex < number of element in list
     * @return - the list passed as the list argument is ascending sorted order on completion
     */
    public static void quicksort(List<Integer> list, int startIndex, int endIndex) {
        if (startIndex - endIndex != 0) {
            if (startIndex < endIndex) {
                comparisons += endIndex - startIndex;
                int p = partition(list, startIndex, endIndex);
                if (p > 0) {
                    //comparisons += (p-1) - startIndex;
                    quicksort(list, startIndex, p - 1);
                }

                if (p < list.size()) {
                    //comparisons += endIndex - (p+1);
                    quicksort(list, p + 1, endIndex);
                }
            }

        }
    }

    /**
     * Returns the index of the median value of the first, middle, and last elements of a list. For
     * lists with even number of elements, the index of number of elements/2 - 1 will be used as the
     * middle index of the list.
     *
     * @param list       - a list of integers with at least one element and no duplicate values.
     * @param startIndex - the start of the subset of the list to be used. Assumed that 0 <
     *                   startIndex <= stopIndex
     * @param endIndex   - the end of the subset of the list to be used. Assumed that startIndex <=
     *                   stopIndex < number of elements in list
     * @return - index of the chosen median value
     */
    public static int medianOfThree(List<Integer> list, int startIndex, int endIndex) {
        int aIndex = startIndex;
        int bIndex = (startIndex + endIndex) / 2;
        int cIndex = endIndex;

        int a = list.get(aIndex);
        int b = list.get(bIndex);
        int c = list.get(cIndex);

        if (a > b) {
            if (c > a) {
                return aIndex;
            } else if (c < b) {
                return bIndex;
            } else {
                return cIndex;
            }
        } else {
            if (c < a) {
                return aIndex;
            } else if (c > b) {
                return bIndex;
            } else {
                return cIndex;
            }
        }
    }
}
