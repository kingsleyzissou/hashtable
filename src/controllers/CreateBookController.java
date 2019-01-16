package controllers;

import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import models.Book;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateBookController implements Initializable {

    @FXML private JFXTextField title;
    @FXML private JFXTextField author;
    @FXML private JFXTextField yearOfPublication;
    @FXML private JFXTextField publisher;
    @FXML private JFXTextField genre;
    @FXML private JFXTextField numberOfPages;
    @FXML private JFXTextArea description;

    private NumberValidator numberValidator = new NumberValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// Call the static utility method to initialise the form
        DashboardController.initialiseBookForm(requiredValidator, numberValidator, title, author, genre, publisher, description, numberOfPages, yearOfPublication);
    }

    /**
     * Validate the form inputs from the GUI
     * to prevent empty values being
     * added to the library
     * 
     * @return true or false, if the form has been validated or not
     */
    public boolean validateForm() {
        if(!title.validate()
                || !author.validate()
                || !genre.validate()
                || !genre.validate()
                || !numberOfPages.validate()
                || !description.validate()
                || !yearOfPublication.validate()) {
            return false;
        }
        return true;
    }

    /**
     * Add a book to the library
     *
     * @throws IOException
     */
    public void addBook() throws IOException {
        if(!this.validateForm()) return;
        String bookTitle = title.getText();
        String bookGenre = genre.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
        String bookDescription = description.getText();
        int bookNumberOfPages = Integer.valueOf(numberOfPages.getText());
        int bookYearOfPublication = Integer.valueOf(yearOfPublication.getText());
        Book book = new Book(bookAuthor,bookTitle, bookPublisher, bookYearOfPublication, bookGenre, bookNumberOfPages, bookDescription);
        DashboardController.library.addBook(book);
        DashboardController.dbc.viewBooks();
    }

    /**
     * Go back to the books view
     *
     * @throws IOException
     */
    public void goBack() throws IOException {
        dbc.viewBooks();
    }


}
