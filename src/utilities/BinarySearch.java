package utilities;

import java.util.Comparator;

public class BinarySearch {

	/**
	 * Static method for user to search an array or hashtable
	 * with default comparator
	 * 
	 * @param needle the item being searched for
	 * @param haystack the array/hastable being searched
	 * @return search results
	 */
    public static <T> int search(T needle, T[] haystack) {
        return search(needle, haystack, 0, haystack.length - 1, (x, y) -> {
            if(x == null) return 1;
            if(y == null) return -1;
            return x.hashCode() - y.hashCode();
        });
    }

    /**
     * Static method for user to search an array or hashtable
	 * with custom comparator
	 * 
	 * @param needle the item being searched for
	 * @param haystack the array/hastable being searched
     * @param c custom comparator
	 * @return search results
     */
    public static <T> int search(T needle, T[] haystack, Comparator<? super T> c) {
        return search(needle, haystack, 0, haystack.length - 1, c);
    }

    /**
     * Static method for user to search an array or hashtable
	 * with custom comparator
	 * 
	 * @param needle the item being searched for
	 * @param haystack the array/hastable being searched
	 * @param start - starting index from which to perform the search
	 * @param end - ending index from which to perform the search
     * @param c custom comparator
	 * @return search results
     */
    public static <T> int search(T needle, T[] haystack, int start, int end, Comparator<? super T> c) {
        // Get the mid point
    	int median = (start + end)/2;

    	// Element has not been found
        if(end < start || median > end) {
            return -1;
        }

        // Compare the search item to the item at the midpoint
        // of the selected range of the array
        int comparison = c.compare(needle, haystack[median]);

        // Elembent has not been found
        if((start == end && median == end) && comparison != 0)
            return -1;

        // The object at the midpoint is a match
        if (comparison == 0)
            return median;

        // The object is smaller than the middle,
        // so search below the midpoint
        if (comparison < 0)
            return search(needle, haystack, start, median, c);

        // The object is larger than the middle,
        // so search above the midpoint
        if (comparison > 0)
            return search(needle, haystack, median + 1, end, c);

        return -1;
    }

}
