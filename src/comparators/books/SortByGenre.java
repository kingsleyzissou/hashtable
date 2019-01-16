package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByGenre implements Comparator<Book>
{
	/**
     * Customer comparator to sort books in ascending
     * order, by genre
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return a.getGenre().compareTo(b.getGenre());
    }
}
