package collections;

import utilities.BinarySearch;
import utilities.LinearSearch;
import utilities.MergeSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class HashTable<E> implements Iterable<E> {

	private E[] hashTable;

	private static final int MAX_PROBES = 500;
	private static final double MAX_LOAD_FACTOR = 0.7;

	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		// convert the selected size to a prime
		int prime = getNextPrime(size);
		// create a new array and cast it to the correct type
	    hashTable = (E[]) new Object[prime];
	}

	/**
	 * The hashfunction used to get the home location index
	 * required for the hashtable
	 * 
	 * @param element to be hashed
	 * @return index or homelocation
	 */
	public int hashFunction(E element) {
		int hash = Math.abs(element.hashCode());
		return hash%hashTable.length;
	}

	/**
	 * Calculate the load factor
	 * i.e. the percentage of used slots
	 * 
	 * @return double - the amount of used slots
	 */
	public double loadFactor() {
		double usedSlots = 0;
		for (E x : hashTable)
			if (x != null)
				usedSlots++;
		return usedSlots / hashTable.length;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Rehash the table if the load factor
	 * has been reached
	 * 
	 */
	private void resize() {

		// Create a new array that is 1.5 larger than the current array
		int size = (int) Math.round(hashTable.length * 1.5);
		// get the next prime number above the new hash size
		int prime = getNextPrime(size);

		// copy the old table
		E[] oldTable = hashTable;
		// create the new table
		hashTable = (E[]) new Object[prime];

		// add the elements from the old to the new
		for(E item : oldTable) {
		    if(item != null) {
                this.addElement(item);
            }
		}

	}

	
	/**
	 * Add an element to the HashTable
	 * 
	 * @param element to be added
	 * @return index of item added
	 */
	public int addElement(E element) {
		int index = hashFunction(element);
		int probeCount = 0;

		// rehash if the load factor is too high
		if(loadFactor() >= MAX_LOAD_FACTOR) {
			this.resize();
		}

		// use quadratic probing until the item has been
		while (loadFactor() < MAX_LOAD_FACTOR && probeCount < MAX_PROBES) {
			// if the location is empty, add the element
			// else probe for the next location quadratically
			if (hashTable[index] == null) {
				hashTable[index] = element;
				return index;
			}
			else {
				probeCount++;
				index = (hashFunction(element) + (probeCount * probeCount)) % hashTable.length;
			}
		}
		return -1;
	}

	/**
	 * Get the size of the HashTable
	 * 
	 * @return
	 */
	public int size() {
		return hashTable.length;
	}

	/**
	 * Get the next consecutive prime number
	 * based on the number provided
	 * 
	 * @param number from which to start looking for the next prime
	 * @return
	 */
	public int getNextPrime(int number) {
		// If the number is prime, return the number (base case)
	    if(isPrime(number)) return number;
	    // increment until a prime is found
	    return getNextPrime(number + 1);
    }

	/**
	 * Checks to see if a number is prime
	 * 
	 * @param number to be checked
	 * @return true if number is prime
	 */
    public boolean isPrime(int number) {
	    int sqrt = (int) Math.sqrt(number) + 1;
	    for(int i = 2; i < sqrt; i ++) {
            if(number%i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove element from the HashTable
     * 
     * @param element
     * @return boolean value whether the remove has been successful or not
     */
    public boolean remove(E element) {
    	// Linearly search for the element
        int index = LinearSearch.linearSearch(hashTable, element);
        if(index >= 0) {
            hashTable[index] = null;
            return true;
        }
        return false;
    }

    /**
     * Get element based on its index
     * 
     * @param index
     * @return the element found
     */
    public E getByIndex(int index) {
		return hashTable[index];
	}

    /**
     * Convert the HashTable into an array
     * 
     * @return
     */
	public E[] toArray() {
		return hashTable;
	}

	/**
	 * Copy the HashTable and return a sorted array
	 * 
	 * @param c custom comparator
	 * @return sorted array
	 */
	public E[] sort(Comparator<E> c) {
        E[] array = Arrays.copyOfRange(hashTable, 0, hashTable.length);
        MergeSort.sort(array, c);
        return array;
    }

	/**
	 * Sort the HashTable and then perform a binary search
	 * for the search term
	 * 
	 * @param needle - the search term
	 * @param c - custom comparator
	 * @return the search result
	 */
	public E search(E needle, Comparator<E> c) {

		// Sort the array
	    E[] haystack = this.sort(c);

	    // Get the index of the element sought
	    int index = BinarySearch.search(needle, haystack, c);

	    // If it is found, i.e. not -1 return the element
	    if(index > 0)
	        return haystack[index];

	    return null;

    }
	
	/**
	 * Check if the HashTable contains an element
	 * 
	 * @param element
	 * @return boolean
	 */
    public boolean contains(E element) {
        for (Object c : hashTable) {
        	if(c != null) {
        		if( c.equals(element)) {
        			return true;
        		}
        	}
        }
        return false;
    }

    /**
     * Find all method, this method is used to find all matches for a search
     * term
     * 
     * @param needle - search term
     * @param c - custom comparator
     * @return LinkList of search results
     */
	public GenericList<E> findAll(E needle, Comparator<E> c) {

		// Sort the array
		E[] haystack = this.sort(c);

		// Binary search for the search term
		int index = BinarySearch.search(needle, haystack, c);

		// Create a new generic list
		GenericList<E> list = new GenericList<>();

		// If the binary search found something,
		// perform a linear search to the left
		// and the right of this index,
		// to find remaining matching elements
		if(index > 0) {
			
			// insert the first element from the binary search
			list.insert(haystack[index]);

			// search to the left and right of the index
			GenericList<E> searchLeft = LinearSearch.searchLeft(haystack, needle, index, c);
			GenericList<E> searchRight = LinearSearch.searchRight(haystack, needle, index, c);

			// merge the results
			list.merge(searchLeft);
			list.merge(searchRight);

			// return the results
			return list;

		}

		return list;

	}

	/**
	 * Return iterator for HashTable
	 * 
	 */
	public Iterator<E> iterator() {
		return new HashTableIterator();
	}
	
	  /**
     * HashTableIterator
     *
     * nested iterator class in the HashTable
     *
     *
     *
     */
    public class HashTableIterator implements Iterator<E> {

		private int currentIndex;

		public HashTableIterator() {
			currentIndex = 0;
		}

		/**
         * Check if there is another element in the list
         * 
         */
		@Override
		public boolean hasNext() {
			return currentIndex < hashTable.length;
		}

		/**
         * Get the contents of the next element in the list
         * 
         */
		@Override
		public E next() {
			return (E) hashTable[currentIndex++];
		}

	}


}
	
	
	
	
	


