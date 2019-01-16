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

public class LinkCharacterToBookController implements Initializable {

    private Book book;
    @FXML JFXListView<Character> characters;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Character character : DashboardController.library.getCharacterTable()) {
            if(character != null)
                characters.getItems().add(character);
        }
        characters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Set the data required for viewing a characters linked to the book.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the books search results.
     *
     */
    public void setData(Book book) {
        this.book = book;
    }

    /**
     * Go back to view the book
     * 
     * @throws IOException
     */
    public void goBack() throws IOException {
        DashboardController.dbc.viewBook(this.book);
    }

    /**
     * Link characters to the book
     * 
     * @throws IOException
     */
    public void addCharacters() throws IOException {
        for(Character character : characters.getSelectionModel().getSelectedItems()) {
            this.book.addCharacter(character);
        }
        DashboardController.dbc.displayToast("Characters linked successfully", 2000);
        this.goBack();
    }
}
