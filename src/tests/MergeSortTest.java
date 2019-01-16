package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import collections.HashTable;
import comparators.characters.SortByName;
import models.Character;
import utilities.BinarySearch;
import utilities.MergeSort;

class MergeSortTest {

	private Integer[] intArray;
	private String[] stringArray;
	private HashTable<Character> characterHashTable;
	 
	
	 @BeforeEach
    public void setUp() throws Exception {
		 
		 intArray = new Integer[20];
		 stringArray = new String[10];
		 characterHashTable = new HashTable<>(20);
		 
		 for(int i = 0; i < intArray.length; i++) {
			 intArray[i] = new Integer((int) (Math.random() * 100 - Math.random() * 10)); 
		 }
		 
		 intArray[7] = 7;
		 
		 stringArray[0] = "cat";
		 stringArray[1] = "bat";
		 stringArray[2] = "mat";
		 stringArray[3] = "rat";
		 stringArray[4] = "sat";
		 stringArray[5] = "pat";
		 stringArray[6] = "gnat";
		 stringArray[7] = "tat";
		 stringArray[8] = "fat";
		 stringArray[9] = "plat";
	
	 }
	
	@Test
	void sortedIntArrayHasLastElementLargerThanFirst() {
		MergeSort.sort(intArray);
		assertTrue(intArray[intArray.length - 1] > intArray[0]);
	}
	
	@Test
	void sortedStringArrayHasLastElementLargerThanFirst() {
		MergeSort.sort(stringArray);
		assertTrue(Math.abs(stringArray[stringArray.length - 1].hashCode()) > Math.abs(stringArray[0].hashCode()));
	}


}
