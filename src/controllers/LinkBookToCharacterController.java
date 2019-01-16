package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import models.Book;
import models.Character;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LinkBookToCharacterController implements Initializable {

    private Character character;
    @FXML private JFXListView<Book> books;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Book book : DashboardController.library.getBookTable()) {
            if(book != null)
                books.getItems().add(book);
        }
        books.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Set the data required for viewing a books linked to the character.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the books search results.
     *
     */
    public void setData(Character character) {
        this.character = character;
    }

    /**
     * Go back to view the character
     * 
     * @throws IOException
     */
    public void goBack() throws IOException {
        DashboardController.dbc.viewCharacter(this.character);
    }

    /**
     * Link books to the character
     * 
     * @throws IOException
     */
    public void addBooks() throws IOException {
        for(Book book : books.getSelectionModel().getSelectedItems()) {
            this.character.addBook(book);
        }
        DashboardController.dbc.displayToast("Books linked successfully", 3000);
        this.goBack();
    }

}
