package comparators.books;

import models.Book;

import java.util.Comparator;

public class SortByAuthor implements Comparator<Book>
{
    /**
     * Customer comparator to sort books in ascending
     * order, by author
     * 
     */
    public int compare(Book a, Book b)
    {
        if(a == null) return 1;
        if(b == null) return -1;
        return a.getAuthor().compareTo(b.getAuthor());
    }
}
