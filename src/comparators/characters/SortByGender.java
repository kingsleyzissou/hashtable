package comparators.characters;

import models.Character;

import java.util.Comparator;

public class SortByGender implements Comparator<Character> {
	
	/**
     * Customer comparator to sort characters in ascending
     * order, by character gender
     * 
     */
    public int compare(Character a, Character b)
    {
        if(a == null) return -1;
        if(b == null) return 1;
        String x = ("" + a.getGender());
        String y = ("" + b.getGender());
        return(x.compareTo(y));
    }
    
}
