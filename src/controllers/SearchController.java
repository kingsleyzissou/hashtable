package controllers;

import collections.GenericList;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Book;
import models.Character;
import utilities.MergeSort;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML private JFXTextField query;
    @FXML private JFXComboBox<String> category;
    @FXML private JFXComboBox<String> bookFilter;
    @FXML private JFXComboBox<String> sort;
    @FXML private JFXComboBox<String> characterFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category.getItems().addAll("Book", "Character");
        
        // Set the sort and filter options
        bookFilter.getItems().addAll("Title", "Author", "Genre", "Publication Year", "Publisher", "Pages");
        sort.getItems().addAll("Ascending", "Descending");
        characterFilter.getItems().addAll("Name", "Gendder", "Age", "Creator");

        // initialise visibility of comboboxes
        this.toggleComboBoxes();

        // Set event listeners that will be used to manage
        // the visibility of the combo boxes
        category.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            if(oldVal == null) {
                if(newVal.equals("Book")) {
                    this.toggleBookComboBoxes();
                }
                if(newVal.equals("Character")) {
                    this.toggleCharacterComboBoxes();
                }
            } else {
                if(newVal.equals("Book")) {
                    this.toggleComboBoxes();
                }
                if(newVal.equals("Character")) {
                    this.toggleComboBoxes();
                }
            }
        });

    }

    /**
     * Toggle the visibility of the book filter ComboBox
     * 
     */
    public void toggleBookComboBoxes() {
        bookFilter.setVisible(!bookFilter.isVisible());
    }

    /**
     * Toggle the visibility of the character filter ComboBox
     * 
     */
    public void toggleCharacterComboBoxes() {
        characterFilter.setVisible(!characterFilter.isVisible());
    }

    /**
     * Toggles all the ComboBoxes
     * 
     */
    public void toggleComboBoxes() {
        this.toggleBookComboBoxes();
        this.toggleCharacterComboBoxes();
    }

    /**
     * Call the relevant search method based 
     * on whether the selected filter is
     * for books or for characters
     * 
     */
    public void search() {
        String q = this.query.getText();
        String cat = this.category.getValue();
        if(cat.equals("Book")) {
            searchBook(q);
        } else {
            searchCharacter(q);
        }
    }

    /**
     * Search for books in the library.
     * 
     * @param query the query string being searched for
     */
    private void searchBook(String query) {
        String filter = this.bookFilter.getValue();
        String sort = this.sort.getValue();
        
        // Get the user defined comparator
        Comparator<Book> c = getBookComparator(filter, sort);

        // Create a book used for comparison
        Book book = createBookForQuery(filter, query);
        
        // Find all matches
        GenericList<Book> results = DashboardController.library.getBookTable().findAll(book, c);

        // Cast results to an array
        Book[] bookResults = bookResultsToArray(results, c);

        // Load the results in a new scene
        this.loadBookResultsView(bookResults);
    }

    /**
     * Search for characters in the library.
     * 
     * @param query the query string being searched for
     */
    private void searchCharacter(String query) {
        String filter = this.characterFilter.getValue();
        String sort = this.sort.getValue();
        
        // Get the user defined comparator
        Comparator<Character> c = getCharacterComparator(filter, sort);

        // Create a character used for comparison
        Character character = createCharacterForQuery(filter, query);
        
        // Find all matches
        GenericList<Character> results = DashboardController.library.getCharacterTable().findAll(character, c);

        // Cast results to an array
        Character[] characterResults = characterResultsToArray(results, c);

        // Load the results in a new scene
        this.loadCharacterResultsView(characterResults);
    }

    /**
     * Load the book results view and pass in the array of results
     * 
     * @param results the results of the search
     */
    private void loadBookResultsView(Book[] results) {
        try {
            DashboardController.dbc.bookResults(results);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the book results view and pass in the array of results
     * 
     * @param results the results of the search
     */
    private void loadCharacterResultsView(Character[] results) {
        try {
            DashboardController.dbc.characterResults(results);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a book based on the filter and sort parameters chosen.
     * This book object will not be added to the library,
     * merely used for comparison purposes.
     * 
     * @param filter the filter selected
     * @param query the query string
     * @return a book based on the query and filter entered in the GUI
     */
    private Book createBookForQuery(String filter, String query) {
        switch(filter) {
            case "Title":
                return new Book(
                        null, query, null, 0, null, 0, null);
            case "Author":
                return new Book(query, null, null, 0, null, 0, null);
            case "Publisher":
                return new Book(null, null, query, 0, null, 0, null);
            case "Year":
                return new Book(null, null, null, Integer.parseInt(query), null, 0, null);
            case "Genre":
                return new Book(null, null, null, 0, query, 0, null);
            case "Pages":
                return new Book(null, null, null, 0, null, Integer.parseInt(query), null);
            default:
                return new Book(null, query, null, 0, null, 0, null);
        }
    }

    /**
     * Create a character based on the filter and sort parameters chosen.
     * This character object will not be added to the library,
     * merely used for comparison purposes.
     * 
     * @param filter the filter selected
     * @param query the query string
     * @return a character based on the query and filter entered in the GUI
     */
    private Character createCharacterForQuery(String filter, String query) {
        switch(filter) {
            case "Name":
                return new Character(query, 'n', 0, null, null);
            case "Gender":
                return new Character(null, query.charAt(0), 0, null, null);
            case "Age":
                return new Character(null, 'n', Integer.parseInt(query), null, null);
            case "Creator":
                return new Character(null, 'n', 0, null, query);

            default:
                return new Character(query, 'n', 0, null, null);
        }
    }

    /**
     * Convert the search results from a LinkList to an array.
     * 
     * @param results LinkList of results
     * @param c the comparator used
     * @return array of results
     */
    private Book[] bookResultsToArray(GenericList<Book> results, Comparator<Book> c) {
        Book[] resultArray = new Book[results.size()];
        int i = 0;
        for(Book bookResult : results) {
            resultArray[i] = bookResult;
            i++;
        }
        MergeSort.sort(resultArray, c);
        return resultArray;
    }

    /**
     * Convert the search results from a LinkList to an array.
     * 
     * @param results LinkList of results
     * @param c the comparator used
     * @return array of results
     */
    private Character[] characterResultsToArray(GenericList<Character> results, Comparator<Character> c) {
        Character[] resultArray = new Character[results.size()];
        int i = 0;
        for(Character characterResult : results) {
            resultArray[i] = characterResult;
            i++;
        }
        MergeSort.sort(resultArray, c);
        return resultArray;
    }

    /**
     * Go back to the home scene
     * 
     * @throws IOException
     */
    public void goBack() throws IOException { DashboardController.dbc.loadHomeScene(); }

    /**
     * Get the custom comparator based on the options
     * selected in the GUI
     * 
     * @param type the type of comparator
     * @param sort the sort direction
     * @return Comparator<Book>
     */
    public Comparator<Book> getBookComparator(String type, String sort) {
        String term = type + " " + sort;
        return DashboardController.getBookComparator(term);
    }

    /**
     * Get the custom comparator based on the options
     * selected in the GUI
     * 
     * @param type the type of comparator
     * @param sort the sort direction
     * @return Comparator<Character>
     */
    private Comparator<Character> getCharacterComparator(String type, String sort) {
        String term = type + " " + sort;
        return DashboardController.getCharacterComparator(term);
    }


}
