package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import comparators.books.*;
import comparators.characters.*;
import models.Book;
import models.Character;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;

import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import models.Library;
import utilities.Validation;

public class DashboardController implements Initializable {

    static Stage modal;

    public static Library library;
    public static DashboardController dbc;

    @FXML private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// Set a static referent to the dashboard controller
        dbc = this;

        // Create a library if none in XML file
        if(library == null) {
            library = new Library(31, 31);
        }

        try{
            this.loadHomeScene();
        } catch (IOException e) {
            System.out.println("Unable to load home page");
        }
    }

    /**
     * Load the home scene
     * 
     * @throws IOException
     */
    @FXML
    void loadHomeScene() throws IOException {
        Parent homeScene = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        borderPane.setCenter(homeScene);
    }

    /**
     * Load the view books scene
     * 
     * @throws IOException
     */
    @FXML
    public void viewBooks() throws IOException {
        Parent booksView = FXMLLoader.load(getClass().getResource("/views/Books.fxml"));
        borderPane.setCenter(booksView);
    }

    /**
     * Load the create book scene
     * 
     * @throws IOException
     */
    void createBook() throws IOException {
        Parent createBookView = FXMLLoader.load(getClass().getResource("/views/CreateBook.fxml"));
        this.borderPane.setCenter(createBookView);
    }

    /**
     * Load the view book scene
     * 
     * @param book to be viewed
     * @throws IOException
     */
    void viewBook(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Book.fxml"));
        Parent ordersView = loader.load();
        BookController controller = loader.getController();
        controller.setData(book);
        this.borderPane.setCenter(ordersView);
    }

    /**
     * Load the update book scene
     * 
     * @param book to be updated
     * @throws IOException
     */
    void updateBook(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/EditBook.fxml"));
        Parent editBookView = loader.load();
        EditBookController controller = loader.getController();
        controller.setData(book);
        this.borderPane.setCenter(editBookView);
    }

    /**
     * Load the link character to books scene
     * 
     * @param book to be updated
     * @throws IOException
     */
    void linkCharacterToBook(Book book) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LinkCharacterToBook.fxml"));
        Parent linkCharacterToBookView = loader.load();
        LinkCharacterToBookController controller = loader.getController();
        controller.setData(book);
        this.borderPane.setCenter(linkCharacterToBookView);
    }

    /**
     * Load the create character scene
     * 
     * @throws IOException
     */
    @FXML
    void createCharacter() throws IOException {
        Parent createCharacterView = FXMLLoader.load(getClass().getResource("/views/CreateCharacter.fxml"));
        this.borderPane.setCenter(createCharacterView);
    }

    /**
     * Load the view characters scene
     * 
     * @throws IOException
     */
    @FXML
    public void viewCharacters() throws IOException {
        Parent charactersView = FXMLLoader.load(getClass().getResource("/views/Characters.fxml"));
        borderPane.setCenter(charactersView);
    }

    /**
     * Load the view character scene
     * 
     * @param character to be viewed
     * @throws IOException
     */
    void viewCharacter(Character character) throws IOException{
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Character.fxml"));
        Parent characterView = loader.load();
        CharacterController controller = loader.getController();
        controller.setData(character);
        this.borderPane.setCenter(characterView);
    }

    /**
     * Load the update character scene
     * 
     * @param character to be updated
     * @throws IOException
     */
    void updateCharacter(Character character) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/EditCharacter.fxml"));
        Parent editCharacterView = loader.load();
        EditCharacterController controller = loader.getController();
        controller.setData(character);
        this.borderPane.setCenter(editCharacterView);
    }

    /**
     * Load the link books to character scene
     * 
     * @param character to be updated
     * @throws IOException
     */
    void linkBookToCharacter(Character character) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LinkBookToCharacter.fxml"));
        Parent linkBookToCharacterView = loader.load();
        LinkBookToCharacterController controller = loader.getController();
        controller.setData(character);
        this.borderPane.setCenter(linkBookToCharacterView);
    }

    /**
     * Load the search view
     * 
     * @throws IOException
     */
    public void searchView() throws IOException {
        Parent searchView = FXMLLoader.load(getClass().getResource("/views/Search.fxml"));
        borderPane.setCenter(searchView);
    }

    /**
     * Load the book results view
     * 
     * @param results
     * @throws IOException
     */
    public void bookResults(Book[] results) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/BookResults.fxml"));
        Parent bookResultsView = loader.load();
        BookResultsController controller = loader.getController();
        controller.setData(results);
        this.borderPane.setCenter(bookResultsView);
    }

    /**
     * Load the character results view
     * 
     * @param results
     * @throws IOException
     */
    public void characterResults(Character[] results) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/CharacterResults.fxml"));
        Parent characterResultsView = loader.load();
        CharacterResultsController controller = loader.getController();
        controller.setData(results);
        this.borderPane.setCenter(characterResultsView);
    }

    /**
     * Display alert box (primarily used to confirm deletion of an object)
     * 
     * @param title for the alert
     * @param message to be displayed
     * @param object to be deleted
     * @param type of the object
     * @throws IOException
     */
    void displayAlertBox(String title, String message, Object object, String type) throws IOException {
        modal =  new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/AlertBox.fxml"));
        Parent alertBox = loader.load();
        AlertBoxController controller = loader.getController();
        controller.setData(message, object, type);
        modal.setScene(new Scene(alertBox));
        modal.showAndWait();
    }

    /**
     * Display a toast message
     * 
     * @param message to be displayed
     * @param duration to show the message
     */
    void displayToast(String message, int duration) {
        JFXSnackbar snackbar = new JFXSnackbar(borderPane);
        snackbar.show(message, duration);
    }

    /**
     * Return comparator based on user defined selections
     * in the GUI
     * 
     * @param term - filter and sort selections
     * @return custom comparator
     */
    public static Comparator<Book> getBookComparator(String term) {
        switch (term) {
            case "Title Ascending": return new SortByTitle();
            case "Title Descending": return new SortByTitleDesc();
            case "Author Ascending": return new SortByAuthor();
            case "Author Descending": return new SortByAuthorDesc();
            case "Genre Ascending": return new SortByGenre();
            case "Genre Descending": return new SortByGenreDesc();
            case "Publisher Ascending": return new SortByPublisher();
            case "Publisher Descending": return new SortByPublisherDesc();
            case "Year Ascending": return new SortByYear();
            case "Year Descending": return new SortByYearDesc();
            case "Pages Ascending": return new SortByPages();
            case "Pages Descending": return new SortByPagesDesc();
            default: return new SortByTitle();
        }
    }

    /**
     * Return comparator based on user defined selections
     * in the GUI
     * 
     * @param term - filter and sort selections
     * @return custom comparator
     */
    public static Comparator<Character> getCharacterComparator(String term) {
        switch (term) {
            case "Name Ascending": return new SortByName();
            case "Name Descending": return new SortByNameDesc();
            case "Gender Ascending": return new SortByGender();
            case "Gender Descending": return new SortByGenderDesc();
            case "Creator Ascending": return new SortByCreator();
            case "Creator Descending": return new SortByCreatorDesc();
            default: return new SortByName();
        }
    }

    /**
     * Initialise the create and edit forms for a book
     * 
     * @param requiredValidator
     * @param numberValidator
     * @param title
     * @param author
     * @param genre
     * @param publisher
     * @param description
     * @param numberOfPages
     * @param yearOfPublication
     */
    static void initialiseBookForm(RequiredFieldValidator requiredValidator, NumberValidator numberValidator, JFXTextField title, JFXTextField author, JFXTextField genre, JFXTextField publisher, JFXTextArea description, JFXTextField numberOfPages, JFXTextField yearOfPublication) {
        // Set the messages for the validators
    	requiredValidator.setMessage("This field is required");
        numberValidator.setMessage("Enter a valid number");

        // Add validators
        title.getValidators().addAll(requiredValidator);
        author.getValidators().addAll(requiredValidator);
        genre.getValidators().addAll(requiredValidator);
        publisher.getValidators().addAll(requiredValidator);
        description.getValidators().addAll(requiredValidator);
        numberOfPages.getValidators().addAll(requiredValidator, numberValidator);
        yearOfPublication.getValidators().addAll(requiredValidator, numberValidator);

        // Add validation listeners
        Validation.addValidationListener(title);
        Validation.addValidationListener(author);
        Validation.addValidationListener(genre);
        Validation.addValidationListener(publisher);
        Validation.addValidationListener(description);
        Validation.addValidationListener(numberOfPages);
        Validation.addValidationListener(yearOfPublication);
    }

    /**
     * Initialise the create and edit forms for a character
     *      
     * @param gender
     * @param requiredValidator
     * @param numberValidator
     * @param name
     * @param description
     * @param creator
     * @param age
     */
    static void initialiseCharacterForm(JFXComboBox<java.lang.Character> gender, RequiredFieldValidator requiredValidator, NumberValidator numberValidator, JFXTextField name, JFXTextArea description, JFXTextField creator, JFXTextField age) {
    	// Set the possible values for Gender
    	gender.getItems().add('M');
        gender.getItems().add('F');

        // Set the messages for the validators
        requiredValidator.setMessage("This field is required");
        numberValidator.setMessage("Please enter a valid number");

        // Add validators
        name.getValidators().addAll(requiredValidator);
        gender.getValidators().addAll(requiredValidator);
        description.getValidators().addAll(requiredValidator);
        creator.getValidators().addAll(requiredValidator);
        age.getValidators().addAll(requiredValidator, numberValidator);

        // Add validation listeners
        Validation.addValidationListener(name);
        Validation.addValidationListener(age);
        Validation.addValidationListener(description);
    }

}
