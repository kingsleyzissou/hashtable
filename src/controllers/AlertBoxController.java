package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import models.Book;
import models.Character;

import java.io.IOException;

import static controllers.DashboardController.*;

public class AlertBoxController {

    private String type;
    private Object object;

    @FXML private Label message;
    @FXML private Button confirm;

    /**
     * Set the data required for an alert box.
     * The alert box is used to display alert, primarily for deleting items.
     *
     * @param message the message to be displayed
     * @param object the object to be deleted
     * @param type the type of object
     */
    void setData(String message, Object object, String type) {
        this.type = type;
        this.object = object;
        this.message.setText(message);
    }

    /**
     * Closes the modal dialog
     *
     */
    public void closeDialog() { modal.close(); }

    /**
     * Carries out the request based on the type of object
     * being actioned
     *
     */
    public void confirmRequest() throws IOException {
        if(this.type.equals("book"))
            this.deleteBook((Book) this.object);
        if(this.type.equals("character"))
            this.deleteCharacter((Character) this.object);
    }

    /**
     * Deletes a book from the library
     *
     * @param book the item to be removed
     */
    private void deleteBook(Book book) throws IOException {
        DashboardController.library.removeBook(book);
        closeDialog();
        dbc.viewBooks();
    }

    /**
     * Deletes a character from the library
     *
     * @param character the item to be removed
     */
    private void deleteCharacter(Character character) throws IOException {
        DashboardController.library.removeCharacter(character);
        closeDialog();
        dbc.viewCharacters();
    }


}
