package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Character;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.MergeSort;

import java.io.IOException;
import java.util.Comparator;

import static controllers.DashboardController.dbc;

public class CharacterResultsController {

    private Character[] results;

    @FXML private TableView<Character> characters;
    @FXML private TableColumn<Character, String> name;
    @FXML private TableColumn<Character, String> gender;
    @FXML private TableColumn<Integer, String> age;
    @FXML private TableColumn<Character, String> creator;

    @FXML private JFXComboBox<String> sort;
    @FXML private JFXComboBox<String> filter;

    @FXML private JFXButton apply;
    @FXML private Label noresults;

    /**
     * Set the data required for viewing a character search results.
     * This method acts as a custom constructor,
     * to provide the data necessary for the 
     * class.
     * 
     * @param the books search results.
     *
     */
    void setData(Character[] results) {
        this.results = results;
        
        // sort options
        sort.getItems().addAll("Name", "Gender", "Age", "Creator");
        filter.getItems().addAll("Ascending", "Descending");

        // show fallback message for search with no results
        if(results.length == 0) {
            noresults.setText("Your search did not return any results");
            characters.setVisible(false);
            sort.setVisible(false);
            filter.setVisible(false);
            apply.setVisible(false);
        }

        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Character> observableCharacters = FXCollections.observableArrayList();
        for(Character character : results) {
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
        MergeSort.sort(results, c);
        
        // Iterate through the results and add them
        // to an observable list for the TableView.
        ObservableList<Character> observableCharacters = FXCollections.observableArrayList();
        for(Object character : results) {
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
     * Go back to the search view
     *
     * @throws IOException
     */
    public void goBack() throws IOException {
        DashboardController.dbc.searchView();
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


