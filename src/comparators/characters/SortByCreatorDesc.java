package comparators.characters;

import models.Character;

import java.util.Comparator;

public class SortByCreatorDesc implements Comparator<Character> {
	
	/**
     * Customer comparator to sort characters in descending
     * order, by character creator
     * 
     */
    public int compare(Character a, Character b)
    {
        if(a == null) return -1;
        if(b == null) return 1;
        return b.getCharCreator().compareTo(a.getCharCreator());
    }
    
}
