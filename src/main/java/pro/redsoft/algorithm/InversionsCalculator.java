package pro.redsoft.algorithm;

import java.util.Arrays;

/**
 * @author Alex Oreshkevich
 */
public class InversionsCalculator {

    // private static final Logger logger = LogManager.getLogger(InversionsCalculator.class);

    private static void debug(Object arg) {
        System.out.println(arg);
    }

    private static void debug(int[] array) {
        //System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public long getInversionsCount(int[] input) {

        final int n = input.length;

        // recursion: base case
        if (n == 1) {
            return 0;
        }

        ArrayWrapper result = sortAndCountInversions(new ArrayWrapper(input));
        if (result != null) {
            return result.count;
        }

        return -1L;
    }

    private ArrayWrapper sortAndCountInversions(ArrayWrapper input) {

        final int length = input.array.length;

        if (length > 2) {

            int firstHalfLength = length / 2;
            ArrayWrapper left = new ArrayWrapper(new int[firstHalfLength]);
            System.arraycopy(input.array, 0, left.array, 0, firstHalfLength);
            ArrayWrapper sortedLeft = sortAndCountInversions(left);

            int rightLength = length - firstHalfLength;
            ArrayWrapper right = new ArrayWrapper(new int[rightLength]);
            System.arraycopy(input.array, firstHalfLength, right.array, 0, rightLength);
            ArrayWrapper sortedRight = sortAndCountInversions(right);

            ArrayWrapper commonResult = merge(sortedLeft, sortedRight);

            return commonResult;
        }
        // do actual sort and return sorted version
        else if (length == 2) {

            if (input.array[0] > input.array[1]) {

                // swap 0 and 1 if they need to be sorted
                int temp = input.array[1];
                input.array[1] = input.array[0];
                input.array[0] = temp;

                // add one inversion to the counter
                input.count++;
            }

            return input;
        } else if (length == 1) {
            return input;
        }

        return null;
    }

    public ArrayWrapper merge(ArrayWrapper left, ArrayWrapper right) {

        int length = left.array.length + right.array.length;
        int[] merged = new int[length];
        ArrayWrapper mergedWrapper = new ArrayWrapper(merged);
        mergedWrapper.count = left.count + right.count;

        for (int i = 0, j = 0, k = 0; k < length; k++) {

            // check both arrays
            if (i < left.array.length && j < right.array.length) {

                if (left.array[i] <= right.array[j]) {
                    merged[k] = left.array[i++];
                } else {
                    merged[k] = right.array[j++];
                    mergedWrapper.count += Math.max(0, left.array.length - i); // split inversions
                }
            }
            // only right array items are present
            else if (i == left.array.length && j < right.array.length) {
                merged[k] = right.array[j++];
            }
            // only left array items are present
            else {
                merged[k] = left.array[i++];
            }
        }

        return mergedWrapper;
    }

    public static class ArrayWrapper {
        int[] array;
        long count;

        public ArrayWrapper(int[] array) {
            this.array = array;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ArrayWrapper {");
            sb.append("\n\tarray=").append(Arrays.toString(array));
            sb.append(", \n\tcount=").append(count);
            sb.append('}');
            return sb.toString();
        }
    }

}
