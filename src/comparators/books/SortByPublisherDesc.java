package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByPublisherDesc implements Comparator<Book>
{
	/**
     * Customer comparator to sort books in descending
     * order, by publisher
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return b.getPublisher().compareTo(a.getPublisher());
    }
}
