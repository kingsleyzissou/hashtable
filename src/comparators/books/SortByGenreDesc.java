package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByGenreDesc implements Comparator<Book>
{
	/**
     * Customer comparator to sort books in descending
     * order, by genre
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return b.getGenre().compareTo(a.getGenre());
    }
}
