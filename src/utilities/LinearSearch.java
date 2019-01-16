package utilities;

import collections.GenericList;

import java.util.Comparator;
import java.util.Iterator;

public class LinearSearch {
	
	/**
	 * Linearly search the array/collection for an element
	 * 
	 * @param collection - the collection being searched through
	 * @param elementSearched - the element being searched
	 * @return true if it has been found
	 */
	public static <L> boolean linearSearch(Iterable<L> collection, L elementSearched) {
		
		Iterator<L> iter = collection.iterator();
		while(iter.hasNext()) {
			
			if(iter.next().equals(elementSearched)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Linearly search the array/collection for an element
	 * 
	 * @param collection - the collection being searched through
	 * @param elementSearched - the element being searched
	 * @return the index of the item found (-1 for not found)
	 */
    public static <L> int linearSearch( L[] list, L elementSearched) {
        int index = 0;
        for(L item : list) {
            if(item != null) {
                if( item.equals(elementSearched)) {
                    return index;
                }
            }
            index++;
        }
        return -1;
    }

    /**
     * Used after a BinarySearch. The method is used to collect
     * all elements to the left that match the element sought after.
     * 
     * @param list being looked through
     * @param elementSearched the item being searched
     * @param start the start point
     * @param c the custom comparator used to sort the collection
     * @return LinkList of all the matches
     */
	public static <L> GenericList<L> searchLeft(L[] list, L elementSearched, int start, Comparator<? super L> c) {
		GenericList<L> collection = new GenericList<>();
		boolean match = true;
		int i = start;
		while(match && i >= 0) {
            int comparison = c.compare(list[i], elementSearched);
			if(comparison == 0) {
				collection.insert(list[i]);
			} else {
				match = false;
			}
			i--;
		}
		return collection;
	}

	/**
     * Used after a BinarySearch. The method is used to collect
     * all elements to the right that match the element sought after.
     * 
     * @param list being looked through
     * @param elementSearched the item being searched
     * @param start the start point
     * @param c the custom comparator used to sort the collection
     * @return LinkList of all the matches
     */
	public static <L> GenericList<L> searchRight(L[] list, L elementSearched, int start, Comparator<? super L> c) {
		GenericList<L> collection = new GenericList<>();
		boolean match = true;
		int i = start;
		while(match && i < list.length) {
            int comparison = c.compare(list[i], elementSearched);
			if(comparison == 0) {
				collection.insert(list[i]);
			} else {
				match = false;
			}
			i++;
		}
		return collection;
	}
	
	
	
}