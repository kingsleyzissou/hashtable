package models;

import collections.GenericList;

public class Book {

    private String title;
    private String genre;
    private int    pages;
    private String author;
    private String publisher;
    private int    publicationYear;
    private String plotDescription;
    private GenericList<Character> characterList;

    public Book(String author, String title, String publisher, int pubYear, String genre, int pages, String plotDesc) {

        this.author          = author;
        this.title 	   		 = title;
        this.publisher 		 = publisher;
        this.publicationYear = pubYear;
        this.genre 			 = genre;
        this.pages			 = pages;
        this.plotDescription = plotDesc;
        this.characterList   = new GenericList<>();

    }

    /**
     * Getters and setters
     * 
     * 
     */
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPlotDescription() {
        return plotDescription;
    }

    public void setPlotDescription(String plotDescription) {
        this.plotDescription = plotDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public GenericList<Character> getCharacterList() {
        return this.characterList;
    }
    
    public String toString() {
        if(this.getTitle() == null) return this.getAuthor();
        return this.getTitle();
    }

    
    /**
     * Custom hashcode method for the book object.
     * The method generates a hashcode based on the
     * fields of the object, multiplying each field's hashcode by
     * the prime number 31.
     * 
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + author.hashCode() + title.hashCode();
        result = prime * result + genre.hashCode() + publisher.hashCode();
        return result;
    }

    /**
     * Add a character to the linklist of associated characters 
     * in the book
     * 
     * @param character
     */
    public void addCharacter(Character character) {
        this.characterList.insert(character);
        if(!character.getBookList().contains(this)) {
            character.addBook(this);
        }
    }

    /**
     * Removes a character from the linklist of associated characters 
     * in the book
     * 
     * @param character
     */
    public void removeCharacter(Character character) {
        this.characterList.remove(character);
        if(character.getBookList().contains(this)) {
            character.removeBook(this);
        }
    }

}
