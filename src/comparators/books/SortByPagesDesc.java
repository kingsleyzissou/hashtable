package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByPagesDesc implements Comparator<Book>
{
	/**
     * Customer comparator to sort books in descending
     * order, by number of pages
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return b.getPages() - a.getPages();
    }
}
