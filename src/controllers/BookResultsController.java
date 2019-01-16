package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Book;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.MergeSort;
import java.io.IOException;
import java.util.Comparator;

import static controllers.DashboardController.dbc;

public class BookResultsController {

    private Book[] results;

    @FXML private TableView<Book> books;
    @FXML private TableColumn<Book, String> title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> publisher;
    @FXML private TableColumn<Book, String> genre;
    @FXML private TableColumn<Book, Integer> yearOfPublication;
    @FXML private TableColumn<Book, Integer> numberOfPages;

    @FXML private JFXComboBox<String> filter;
    @FXML private JFXComboBox<String> sort;
    @FXML private JFXButton apply;
    @FXML private Label noresults;

    
    /**
     * Set the data required for viewing a book search results.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the books search results.
     *
     */
    void setData(Book[] results) {
        this.results = results;

        // sort options
        sort.getItems().addAll("Title", "Author", "Pages", "Year", "Publisher", "Genre");
        filter.getItems().addAll("Ascending", "Descending");

        // show fallback message for search with no results
        if(results.length == 0) {
            noresults.setText("Your search did not return any results");
            books.setVisible(false);
            sort.setVisible(false);
            filter.setVisible(false);
            apply.setVisible(false);
        }

        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Book> observableBooks = FXCollections.observableArrayList();
        for(Book book : results) {
            if(book != null)
                observableBooks.add(book);
        }
        
        // Set the TableView columns
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        yearOfPublication.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        numberOfPages.setCellValueFactory(new PropertyValueFactory<>("pages"));
        books.setItems(observableBooks);
    }

    
    /**
     * Sort the book results based on the comparator chosen
     * in the GUI
     * 
     */
    public void sort() {
        // Get the selected comparator
        Comparator<Book> c = getBookComparator(sort.getValue(), filter.getValue());
        
        // Sort the books using merge sort algorithm
        MergeSort.sort(this.results, c);
        
        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Book> observableBooks = FXCollections.observableArrayList();
        for(Book book : results) {
            if(book != null)
                observableBooks.add(book);
        }
        
        // Set the TableView columns
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        yearOfPublication.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        numberOfPages.setCellValueFactory(new PropertyValueFactory<>("pages"));
        books.setItems(observableBooks);
    }

    /**
     * Go to the create book view
     *
     * @throws IOException
     */
    @FXML
    public void createBook() throws IOException {
        dbc.createBook();
    }

    /**
     * View a book
     * 
     * @throws IOException
     */
    @FXML
    private void viewBook() throws IOException {
        Book book = books.getSelectionModel().getSelectedItem();
        if (book != null) dbc.viewBook(book);
    }

    /**
     * Display the confirm delete modal to cancel a booking
     *
     * @throws IOException
     */
    public void deleteBook() throws IOException {
        Book book = books.getSelectionModel().getSelectedItem();
        if (book != null) {
            String message = "Are you sure you want to delete\n"
                    + book.getTitle() + "?";
            dbc.displayAlertBox("Delete Book", message, book, "book");
        }
    }

    /**
     * Go back to the books view
     *
     * @throws IOException
     */
    @FXML
    public void goBack() throws IOException { dbc.searchView(); }

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

}
