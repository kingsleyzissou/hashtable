package comparators.characters;

import models.Character;

import java.util.Comparator;

public class SortByNameDesc implements Comparator<Character> {
	
	/**
     * Customer comparator to sort characters in descending
     * order, by character name
     * 
     */
    public int compare(Character a, Character b)
    {
        if(a == null) return -1;
        if(b == null) return 1;
        return b.getName().compareTo(a.getName());
    }
    
}
