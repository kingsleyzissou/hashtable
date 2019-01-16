package utilities;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

	/**
	 * Static method used to sort an array,
	 * using a default comparator
	 * 
	 * @param array to be sorted
	 */
    public static <T> void sort(T[] array) {
        sort(array, (x, y) -> {
            if(x == null) return 1;
            if(y == null) return -1;
           return x.hashCode() - y.hashCode();
        });
    }

	/**
	 * Static method used to sort an array,
	 * using a custom comparator
	 * 
	 * @param array to be sorted
	 * @parm c custom comparator
	 */
    public static <T> void sort(T[] array, Comparator<T> c) {
        sort(array, 0, (array.length - 1), c);
    }

    /**
     * Static method used to sort an array,
	 * using a custom comparator
	 * 
	 * @param array to be sorted
     * @param low - starting index from which to sort
     * @param high - ending index to which to sort
     * @param c comparator used for the sort
     */
    private static <T> void sort(T[] array, int low, int high, Comparator<T> c) {
        if(high > low) {
        	// get the mid point
            int middle = (array.length) / 2;
            
            // split the array into an array left of the midpoint
            // and an array to the right of the midpoint
            T[] left = Arrays.copyOfRange(array, low, middle);
            T[] right = Arrays.copyOfRange(array, middle, high + 1);
            
            // sort the left array
            sort(left, c);
            // sort the right array
            sort(right, c);
            
            // merge the resulting arrays
            merge(array, left, right, c);
        }
    }

    /**
     * Merge the sorted left and right arrays
     * 
     * @param array - the original array
     * @param left - the left array
     * @param right - the right array
     * @param c - the comparator used
     */
    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<T> c) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
        	// if the item to the left is larger,
        	// place the left item first, otherwise
        	// place the right item first
        	// until one of the arrays has been exhausted
            if(c.compare(left[i], right[j]) <= 0) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
        
        // If the left array has not been exhausted,
        // add the remaining elements
        while (i < left.length) {
            array[k] = left[i];
            k++; i++;
        }
        
        // If the right array has not been exhausted,
        // add the remaining elements
        while (j < right.length) {
            array[k] = right[j];
            k++; j++;
        }
    }


}
