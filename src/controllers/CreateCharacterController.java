package controllers;

import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import models.Character;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateCharacterController implements Initializable {

    @FXML private JFXTextField name;
    @FXML private JFXTextField age;
    @FXML private JFXTextField creator;
    @FXML private JFXTextArea description;
    @FXML private JFXComboBox<java.lang.Character> gender;

    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();
    private NumberValidator numberValidator = new NumberValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// Call the static utility method to initialise the form
        DashboardController.initialiseCharacterForm(gender, requiredValidator, numberValidator, name, description, creator, age);
    }

    /**
     * Validate the form inputs from the GUI
     * to prevent empty values being
     * added to the library
     * 
     * @return true or false, if the form has been validated or not
     */
    public boolean validateForm() {
        if(!name.validate()
                || !gender.validate()
                || !age.validate()
                || !description.validate()
                || !creator.validate()) {
            return false;
        }
        return true;
    }

    /**
     * Add a character to the library
     *
     * @throws IOException
     */
    public void addCharacter() throws IOException {
        if(!this.validateForm()) return;
        String characterName = name.getText();
        java.lang.Character characterGender = gender.getValue();
        int characterAge = Integer.parseInt(age.getText());
        String characterCreator = creator.getText();
        String characterDescription = description.getText();
        Character character = new Character(characterName, characterGender, characterAge, characterDescription, characterCreator);
        DashboardController.library.addCharacter(character);
        DashboardController.dbc.viewCharacters();
    }

    /**
     * Go back to the characters view
     *
     * @throws IOException
     */
    public void goBack() throws IOException {
        dbc.viewCharacters();
    }

}
