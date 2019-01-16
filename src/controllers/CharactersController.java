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
import models.Character;

import static controllers.DashboardController.*;

public class CharactersController implements Initializable {

    @FXML private TableView<Character> characters;
    @FXML private TableColumn<Character, String> name;
    @FXML private TableColumn<Character, String> gender;
    @FXML private TableColumn<Integer, String> age;
    @FXML private TableColumn<Character, String> creator;

    @FXML private JFXComboBox<String> sort;
    @FXML private JFXComboBox<String> filter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // sort options
        sort.getItems().addAll("Name", "Gender", "Age", "Creator");
        filter.getItems().addAll("Ascending", "Descending");

        HashTable<Character> cs = DashboardController.library.getCharacterTable();
        
        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Character> observableCharacters = FXCollections.observableArrayList();
        for(Character character : cs) {
            if(character != null)
                observableCharacters.add(character);
        }
        
        // Set the TableView columns
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        creator.setCellValueFactory(new PropertyValueFactory<>("charCreator"));
        characters.setItems(observableCharacters);
    }

    public void sort() {
        // Get the selected comparator
        Comparator<Character> c = getCharacterComparator(sort.getValue(), filter.getValue());
        
        // Sort the characters using merge sort algorithm 
        Object[] characterArray = DashboardController.library.getCharacterTable().sort(c);
        
        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Character> observableCharacters = FXCollections.observableArrayList();
        for(Object character : characterArray) {
            if(character != null)
                observableCharacters.add((Character) character);
        }
        
        // Set the TableView columns
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        creator.setCellValueFactory(new PropertyValueFactory<>("charCreator"));
        characters.setItems(observableCharacters);
    }

    /**
     * Go to the create character view
     *
     * @throws IOException
     */
    @FXML
    public void createCharacter() throws IOException {
        dbc.createCharacter();
    }

    /**
     * View character
     *
     * @throws IOException
     */
    @FXML
    private void viewCharacter() throws IOException {
        Character character = characters.getSelectionModel().getSelectedItem();
        if (character != null) dbc.viewCharacter(character);
    }

    /**
     * Display the confirm delete modal to cancel a booking
     *
     * @throws IOException
     */
    public void deleteCharacter() throws IOException {
        Character character = characters.getSelectionModel().getSelectedItem();
        if (character != null) {
            String message = "Are you sure you want to delete\n"
                    + character.getName() + "?";
            dbc.displayAlertBox("Delete Character", message, character, "character");
        }
    }

    /**
     * Get the custom comparator based on the options
     * selected in the GUI
     * 
     * @param type the type of comparator
     * @param sort the sort direction
     * @return Comparator<Character>
     */
    private Comparator<Character> getCharacterComparator(String type, String sort) {
        String term = type + " " + sort;
        return DashboardController.getCharacterComparator(term);
    }

}
