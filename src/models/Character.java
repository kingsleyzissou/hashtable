package models;

import collections.GenericList;

public class Character {

    private String name;
    private char   gender;
    private int	   age;
    private String description;
    private String charCreator;
    private GenericList<Book> bookList;

    public Character(String name, char gender, int age, String desc,  String charCreator) {
        this.name         	 = name;
        this.gender     	 = gender;
        this.age         	 = age;
        this.description 	 = desc;
        this.charCreator     = charCreator;
        this.bookList        = new GenericList<>();
    }
    
    /**
     * Getters and setters
     * 
     * 
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCharCreator() {
        return charCreator;
    }

    public void setCharCreator(String charCreator) {
        this.charCreator = charCreator;
    }
    
    
    public GenericList<Book> getBookList() {
        return this.bookList;
    }

    /**
     * Add a book to the linklist of associated books 
     * to the character
     * 
     * @param book
     */
    public void addBook(Book book) {
        this.bookList.insert(book);
        if(!book.getCharacterList().contains(this)) {
            book.addCharacter(this);
        }
    }

    /**
     * Remove a book from the linklist of associated books 
     * to the character
     * 
     * @param book
     */
    public void removeBook(Book book) {
        this.bookList.remove(book);
        if(book.getCharacterList().contains(this)) {
            book.removeCharacter(this);
        }
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
        result = prime * result + name.hashCode() + description.hashCode() + charCreator.hashCode();
        return result;
    }

    public String toString() {
        return this.name;
    }

}
