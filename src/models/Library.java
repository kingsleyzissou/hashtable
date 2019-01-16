package models;


import collections.HashTable;

public class Library {
    private HashTable<Book> bookTable;
    private HashTable<Character> characterTable;

    public Library() {
        this.bookTable = new HashTable<>(31);
        this.characterTable = new HashTable<>(31);
    }

    public Library(int bookSize, int characterSize) {
        this.bookTable = new HashTable<>(bookSize);
        this.characterTable = new HashTable<>(characterSize);
    }

    /**
     * Add a book to the library
     * 
     * @param book
     */
    public void addBook(Book book) {
        bookTable.addElement(book);
    }

    /**
     * Add a character to the library
     * 
     * @param character
     */
    public void addCharacter(Character character) {
        characterTable.addElement(character);
    }

    /**
     * Remove a book from the library
     * 
     * @param book
     */
    public void removeBook(Book book) {
    	for(Character character : book.getCharacterList()) {
    		character.removeBook(book);
    	}		
        this.bookTable.remove(book);
    }

    /**
     * Remove a character from the library
     * 
     * @param character
     */
    public void removeCharacter(Character character) {
    	for(Book book : character.getBookList()) {
    		book.removeCharacter(character);
    	}	
        this.characterTable.remove(character);
    }


    public HashTable<Book> getBookTable() {
        return this.bookTable;
    }

    public HashTable<Character> getCharacterTable() {
        return this.characterTable;
    }



}
