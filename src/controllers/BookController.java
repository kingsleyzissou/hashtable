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

public class BookController {

    Book book;
    @FXML private Label title;
    @FXML private Label bookCharacters;
    @FXML private Label author;
    @FXML private Label publisher;
    @FXML private Label year;
    @FXML private Label pages;
    @FXML private Label description;
    @FXML private TableView<Character> characters;
    @FXML private TableColumn<Character, String> name;
    @FXML private TableColumn<Character, String> gender;

    /**
     * Set the data required for viewing a single book.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the book being viewed.
     *
     */
    void setData(Book book) {
        this.book = book;
        title.setText(book.getTitle());
        bookCharacters.setText("Characters that are in " + book.getTitle());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        year.setText("" + book.getPublicationYear());
        pages.setText("" + book.getPages());
        description.setText(book.getPlotDescription());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        characters.setItems(this.getObservableCharacterList());
    }

    /**
     * Switch views to the scene where the book can be edited
     * 
     * @throws IOException
     */
    @FXML
    public void editBook() throws IOException { dbc.updateBook(this.book); }

    /**
     * Switch views to the scene where the character 
     * associations can be removed from the book.
     * 
     * @throws IOException
     */
    @FXML
    public void unlinkCharacter() throws IOException {
        Character character = characters.getSelectionModel().getSelectedItem();
        this.book.removeCharacter(character);
        this.goBack();
    }

    /**
     * Switch views to the scene where the character 
     * associations can be added to the book.
     * 
     * @throws IOException
     */
    @FXML
    public void linkCharacter() throws IOException { DashboardController.dbc.linkCharacterToBook(this.book); }

    /**
     * Switch views to the scene where a single character 
     * selection can be viewed.
     * 
     * @throws IOException
     */
    
    public void viewCharacter() throws IOException {
        Character character = characters.getSelectionModel().getSelectedItem();
        DashboardController.dbc.viewCharacter(character);
    }

    /**
     * Switch back to the bookings view
     *
     * @throws IOException
     */
    @FXML
    public void goBack() throws IOException { dbc.viewBooks(); }


    /**
     * Display the confirm delete modal to cancel a booking
     *
     * @throws IOException
     */
    @FXML
    public void deleteBook() throws IOException {
        String message = "Are you sure you want to delete\n"
                + this.book.getTitle() + "?";
        dbc.displayAlertBox("Delete book", message, this.book, "book");
    }

    
    /**
     * Creates a custom observable list of characters to 
     * be used in the table view.
     * 
     * @return ObservableList<Character>
     */
    private ObservableList<Character> getObservableCharacterList() {

        ObservableList<Character> characterList = FXCollections.observableArrayList();

        for(Character character : this.book.getCharacterList()) {
            characterList.add(character);
        }

        return characterList;

    }

}
