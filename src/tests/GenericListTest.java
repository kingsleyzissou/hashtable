package tests;

import static org.junit.jupiter.api.Assertions.*;

import collections.GenericList;
import models.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericListTest {

	private GenericList<String> testGenericList;
	private GenericList<Integer> testGenericListInt;
	private GenericList<Object> testGenericListObject;
	private Character character1 = new Character("bob",'M', 21, "young guy",  "JRR tolkien");
	private Character character2 = new Character("paul",'m', 1, "young baby",  "jk rowling");
	private Character character3 = new Character("sauron",'M', 99999, "evil beyond reason",  "JRR tolkien");
	private Character character4 = new Character("Mary Magdalene",'F', 21, "mythical woman",  "unknown");
	private Character character5 = new Character("dorothy",'f', 21, "young girl",  "L.Frank Baum");

	@BeforeEach
	public void setUp() throws Exception {
		testGenericList = new GenericList<>();
		testGenericList.insert("paul");
		testGenericList.insert("dog");
		testGenericList.insert("cat");
		testGenericList.insert("fish");
		testGenericList.insert("plant");

		testGenericListInt = new GenericList<>();
		testGenericListInt.insert(20);
		testGenericListInt.insert(255);
		testGenericListInt.insert(1);
		testGenericListInt.insert(86);
		testGenericListInt.insert(22367);

		testGenericListObject = new GenericList<>();
		testGenericListObject.insert(character1);
		testGenericListObject.insert(character2);
		testGenericListObject.insert(character3);
		testGenericListObject.insert(character4);
		testGenericListObject.insert(character5);



	}

	@Test
	void testInsert() {
		assertEquals(true,testGenericList.contains("paul"));
		assertEquals(true,testGenericListInt.contains(1));
		assertEquals(true, testGenericListObject.contains(character5));
	}

	@Test
	void testContains() {
		assertEquals(true, testGenericList.contains("paul"));
		assertEquals(true, testGenericListInt.contains(1));
		assertEquals(true, testGenericListObject.contains(character5));
	}

	@Test
	void testRemove() {
		assertEquals(true, testGenericList.remove("paul"));
		assertEquals(true, testGenericListInt.remove(1));
		assertEquals(true, testGenericListObject.remove(character5));
	}

	@Test
	void testGetContents() {
		assertEquals("plant", testGenericList.getContents("plant").toString());
		assertEquals("Mary Magdalene", testGenericListObject.getContents(character4).toString());
	}
}
