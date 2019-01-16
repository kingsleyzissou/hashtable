package tests;

import static org.junit.jupiter.api.Assertions.*;

import collections.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.Character;
import utilities.BinarySearch;
import utilities.LinearSearch;

class HashTableTest {

    private HashTable<String> stringHashTable;
    private HashTable<Integer> intHashTable;
    private HashTable<Character> characterHashTable;
    
    private Character character1 = new Character("bob",'M', 21, "young guy",  "JRR tolkien");
    private Character character2 = new Character("paul",'m', 1, "young baby",  "jk rowling");
    private Character character3 = new Character("sauron",'M', 99999, "evil beyond reason",  "JRR tolkien");
    private Character character4 = new Character("Mary Magdalene",'F', 21, "mythical woman",  "unknown");
    private Character character5 = new Character("dorothy",'f', 21, "young girl",  "L.Frank Baum");
    private Character character6 = new Character("mickey mouse", 'M', 90, "an old mouse", "Walt Disney");
    
    
    private int intIndex;
    private int stringIndex;
    private int characterIndex;
    
    @BeforeEach
    public void setUp() throws Exception {



        stringHashTable = new HashTable<>(10);
        stringIndex = stringHashTable.addElement("cat");
        stringHashTable.addElement("paul");
        stringHashTable.addElement("dog");
        stringHashTable.addElement("fish");
        stringHashTable.addElement("plant");

        intHashTable = new HashTable<>(10);
        intIndex = intHashTable.addElement(255);
        intHashTable.addElement(20);
        intHashTable.addElement(1);
        intHashTable.addElement(86);
        intHashTable.addElement(22367);

        characterHashTable = new HashTable<>(10);
        characterIndex =characterHashTable.addElement(character1);
        characterHashTable.addElement(character2);
        characterHashTable.addElement(character3);
        characterHashTable.addElement(character4);
        characterHashTable.addElement(character5);
        characterHashTable.addElement(character6);

    }

    @Test
    void testAddElement() {
        stringHashTable.addElement("tomato");
        assertEquals(true, stringHashTable.contains("paul"));
        intHashTable.addElement(1916);
        assertEquals(true, intHashTable.contains(20));
        characterHashTable.addElement(character6);
        assertEquals(true, characterHashTable.contains(character1));
    }

    @Test
    void testRemove() {
        assertTrue(stringHashTable.remove("paul"));
        assertTrue(intHashTable.remove(1));
        assertTrue(characterHashTable.remove(character6));
    }

    @Test
    void testGetByIndex() {
        assertEquals("cat", stringHashTable.getByIndex(stringIndex));
        assertEquals(new Integer(255), intHashTable.getByIndex(intIndex));
        assertEquals(character1, characterHashTable.getByIndex(characterIndex));
    }
    
}

