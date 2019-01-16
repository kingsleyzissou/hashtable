package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import collections.HashTable;
import comparators.characters.SortByName;
import models.Character;
import utilities.BinarySearch;
import utilities.MergeSort;

class BinarySearchTest {

	 private Integer[] intArray;
	 private String[] stringArray;
	 private HashTable<Character> characterHashTable;
	 
	 private Character character1 = new Character("bob",'M', 21, "young guy",  "JRR tolkien");
	 private Character character2 = new Character("paul",'m', 1, "young baby",  "jk rowling");
	 private Character character3 = new Character("sauron",'M', 99999, "evil beyond reason",  "JRR tolkien");
	 private Character character4 = new Character("Mary Magdalene",'F', 21, "mythical woman",  "unknown");
	 private Character character5 = new Character("dorothy",'f', 21, "young girl",  "L.Frank Baum");
	 private Character character6 = new Character("mickey mouse", 'M', 90, "an old mouse", "Walt Disney");
	
	 @BeforeEach
     public void setUp() throws Exception {
		 
		 intArray = new Integer[20];
		 stringArray = new String[10];
		 characterHashTable = new HashTable<>(20);
		 
		 for(int i = 0; i < intArray.length; i++) {
			 intArray[i] = new Integer((int) Math.random()); 
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
	
	     characterHashTable.addElement(character1);
	     characterHashTable.addElement(character2);
	     characterHashTable.addElement(character3);
	     characterHashTable.addElement(character4);
	     characterHashTable.addElement(character5);
	     characterHashTable.addElement(character6);
	 }
	
	@Test
	void searchReturnsElementIfInIntegerArray() {
		MergeSort.sort(intArray);
		int index = BinarySearch.search(7, intArray);
		assertNotEquals(-1, index);
		assertEquals(new Integer(7), intArray[index]);
	}
	
	@Test
	void searchReturnsElementIfInStringArray() {
		MergeSort.sort(stringArray);
		int index = BinarySearch.search("gnat", stringArray);
		assertNotEquals(-1, index);
		assertEquals("gnat", stringArray[index]);
	}
	
	@Test
	void elementNotInArrayReturnsIndexOfMinusOne() {
		MergeSort.sort(stringArray);
		int index = BinarySearch.search("boat", stringArray);
		assertEquals(-1, index);
	}
	
	@Test
	void searchCharacterTableByName() {
		Character characterSought = characterHashTable.search(character3, new SortByName());
		assertEquals(character3, characterSought);
	}
	
	
}
