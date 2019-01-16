package controllers;

import java.io.IOException;
import java.net.URL;

import collections.HashTable;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Book;

import static controllers.DashboardController.*;

public class BooksController implements Initializable {

    @FXML private TableView<Book> books;
    @FXML private TableColumn<Book, String> title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> publisher;
    @FXML private TableColumn<Book, String> genre;
    @FXML private TableColumn<Book, Integer> yearOfPublication;
    @FXML private TableColumn<Book, Integer> numberOfPages;

    @FXML private JFXComboBox<String> filter;
    @FXML private JFXComboBox<String> sort;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// sort options
        sort.getItems().addAll("Title", "Author", "Pages", "Year", "Publisher", "Genre");
        filter.getItems().addAll("Ascending", "Descending");

        // Iterate through the results and add them
        // to an observable list for the TableView.
        HashTable<Book> bs = DashboardController.library.getBookTable();
        ObservableList<Book> observableBooks = FXCollections.observableArrayList();
        for(Book book : bs) {
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
     * Sort the books based on the comparator chosen
     * in the GUI
     * 
     */
    public void sort() {
        Comparator<Book> c = getBookComparator(sort.getValue(), filter.getValue());
        
     // Sort the books using merge sort algorithm
        Object[] bookArray = DashboardController.library.getBookTable().sort(c);
        
        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Book> observableBooks = FXCollections.observableArrayList();
        for(Object book : bookArray) {
            if(book != null)
                observableBooks.add((Book) book);
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
     * Go to the create booking view
     *
     * @throws IOException
     */
    @FXML
    public void createBook() throws IOException {
        dbc.createBook();
    }

    /**
     * View a booking
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
