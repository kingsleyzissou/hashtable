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

public class EditBookController implements Initializable {

    @FXML private JFXTextField title;
    @FXML private JFXTextField author;
    @FXML private JFXTextField yearOfPublication;
    @FXML private JFXTextField publisher;
    @FXML private JFXTextField genre;
    @FXML private JFXTextField numberOfPages;
    @FXML private JFXTextArea description;

    private Book book;
    private NumberValidator numberValidator = new NumberValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// Call the static utility method to initialise the form
        initialiseBookForm(requiredValidator, numberValidator, title, author, genre, publisher, description, numberOfPages, yearOfPublication);
    }
    
    /**
     * Set the data required for updating a single book.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the book being updated.
     *
     */
    void setData(Book book) {
        this.book = book;
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        publisher.setText(book.getPublisher());
        yearOfPublication.setText("" + book.getPublicationYear());
        numberOfPages.setText("" + book.getPages());
        description.setText(book.getPlotDescription());
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
     * Update the book in the library
     *
     * @throws IOException
     */
    public void updateBook() throws IOException {
        if(!this.validateForm()) return;
        this.book.setTitle(title.getText());
        this.book.setAuthor(author.getText());
        this.book.setPublisher(publisher.getText());
        this.book.setGenre(genre.getText());
        this.book.setPlotDescription(description.getText());
        this.book.setPublicationYear(Integer.valueOf(yearOfPublication.getText()));
        this.book.setPages(Integer.valueOf(numberOfPages.getText()));

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
