package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Book;
import models.Character;


import java.io.IOException;

import static controllers.DashboardController.*;

public class CharacterController {

    private Character character;
    @FXML private Label name;
    @FXML private Label gender;
    @FXML private Label age;
    @FXML private Label creator;
    @FXML private Label description;
    @FXML private Label characterBooks;
    @FXML private TableView<Book> books;
    @FXML private TableColumn<Book, String> bookTitle;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> publisher;
    @FXML private TableColumn<Book, Integer> yearOfPublication;
    @FXML private TableColumn<Book, Integer> numberOfPages;


    /**
     * Set the data required for viewing a single character.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the character being viewed.
     *
     */
    void setData(Character character) {
        this.character = character;
        this.characterBooks.setText("Books " + this.character.getName() + " is in");
        name.setText(this.character.getName());
        gender.setText("" + this.character.getGender());
        age.setText("" + this.character.getAge());
        creator.setText(this.character.getCharCreator());
        description.setText(this.character.getDescription());
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        yearOfPublication.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        numberOfPages.setCellValueFactory(new PropertyValueFactory<>("pages"));
        books.setItems(this.getObservableBookList());
    }

    /**
     * Switch views to the scene where the book can be viewed
     * 
     * @throws IOException
     */
    @FXML
    public void viewBook() throws IOException {
        Book book = books.getSelectionModel().getSelectedItem();
        DashboardController.dbc.viewBook(book);
    }

    /**
     * Switch views to the scene where the book 
     * associations can be removed from the character.
     * 
     * @throws IOException
     */
    @FXML
    public void unlinkBook() throws IOException {
        Book book = books.getSelectionModel().getSelectedItem();
        this.character.removeBook(book);
        this.goBack();
    }

    /**
     * Switch views to the scene where the book 
     * associations can be added to the character.
     * 
     * @throws IOException
     */
    @FXML
    public void linkBook() throws IOException { DashboardController.dbc.linkBookToCharacter(this.character); }

    /**
     * Switch views to the scene where the character can be edited
     * 
     * @throws IOException
     */
    @FXML
    public void editCharacter() throws IOException { dbc.updateCharacter(this.character); }

    /**
     * Go back to the bookings view
     *
     * @throws IOException
     */
    @FXML
    public void goBack() throws IOException { dbc.viewCharacters(); }


    /**
     * Display the confirm delete modal to cancel a booking
     *
     * @throws IOException
     */
    @FXML
    public void deleteCharacter() throws IOException {
        String message = "Are you sure you want to cancel the booking for\n"
                + this.character.getName() + "?";
        dbc.displayAlertBox("Delete character", message, this.character, "character");
    }

    /**
     * Creates a custom observable list of books to 
     * be used in the table view.
     * 
     * @return ObservableList<Book>
     */
    private ObservableList<Book> getObservableBookList() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        for(Book book : this.character.getBookList())
            bookList.add(book);
        return bookList;
    }

}
