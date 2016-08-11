package pro.redsoft.algorithm;

/**
 * @author Alex Oreshkevich
 */
public class BinarySearch {

    /**
     * Implements search using binary search algorithm for the given array.
     *
     * @return index of the target element, or -1 if it's not found
     */
    public static int search(int[] array, int target) {
        return binarySearch(array, 0, array.length - 1, target);
    }

    private static int binarySearch(int[] array, int startInd, int endIndex, int target) {

        int length = endIndex - startInd + 1;
        if (length == 1) {

            // target element is found
            if (array[startInd] == target) {
                return startInd;
            } else {
                return -1;
            }
        } else {
            int newEndInd = startInd + length / 2 - 1;

            int val = array[newEndInd];
            if (val == target) {
                return newEndInd;
            }

            if (val > target) {
                return binarySearch(array, startInd, newEndInd, target);
            } else {
                return binarySearch(array, newEndInd + 1, endIndex, target);
            }
        }
    }

    public static void main(String[] args) {

        int[] test = new int[]{1, 2, 3, 4, 5};
        System.out.println(search(test, 2));
    }
}
