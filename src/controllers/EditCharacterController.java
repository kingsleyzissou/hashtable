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

public class EditCharacterController implements Initializable {

    @FXML private JFXTextField name;
    @FXML private JFXTextField age;
    @FXML private JFXTextField creator;
    @FXML private JFXTextArea description;
    @FXML private JFXComboBox<java.lang.Character> gender;

    private Character character;
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();
    private NumberValidator numberValidator = new NumberValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// Call the static utility method to initialise the form
        DashboardController.initialiseCharacterForm(gender, requiredValidator, numberValidator, name, description, creator, age);
    }

    /**
     * Set the data required for updating a single character.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the character being updated.
     *
     */
    void setData(Character character) {
        this.character = character;
        name.setText(character.getName());
        gender.setValue(character.getGender());
        age.setText("" + character.getAge());
        creator.setText(character.getCharCreator());
        description.setText(character.getDescription());
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
     * Update the character in the library
     *
     * @throws IOException
     */
    public void updateCharacter() throws IOException {
        if(!this.validateForm()) return;
        this.character.setName(name.getText());
        this.character.setGender(gender.getValue());
        this.character.setAge(Integer.parseInt(age.getText()));
        this.character.setCharCreator(creator.getText());
        this.character.setDescription(description.getText());
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
