package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByYear implements Comparator<Book>
{
	/**
     * Customer comparator to sort books in ascending
     * order, by publication year
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return a.getPublicationYear() - b.getPublicationYear();
    }
}
