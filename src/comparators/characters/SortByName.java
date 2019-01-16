package comparators.characters;

import models.Character;

import java.util.Comparator;

public class SortByName implements Comparator<Character> {
	
	/**
     * Customer comparator to sort characters in ascending
     * order, by character name
     * 
     */
    public int compare(Character a, Character b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return a.getName().compareTo(b.getName());
    }
    
}
