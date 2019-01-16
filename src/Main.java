import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import controllers.DashboardController;
import models.Library;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Book/Library infromation management systems using custom HashTable ADT
 *
 * @version 1.0
 * @author      Andrew Brennan          <20079247>
 * @author      Gianluca Zuccarelli     <20079110>
 */

public class Main extends Application {

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        load();

        Parent root = FXMLLoader.load(getClass().getResource("views/Dashboard.fxml"));
        Scene scene = new Scene(root, 1000, 800);
        String css = Main.class.getResource("snackbar.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Books and tings");
        stage.setScene(scene);
        stage.show();

        stage.setOnHiding(event -> {
            try {
                this.save();
            } catch(IOException e) {
                System.out.println("Unable to save to disk");
            }
        });

    }

    /**
     * Write the restaurant object and data to a json file
     *
     * References:
     * https://github.com/google/gson/blob/master/UserGuide.md
     * https://stackoverflow.com/questions/29319434/how-to-save-data-with-gson-in-a-json-file
     *
     * @throws IOException throws an IOException if the data cannot be saved to file
     */
    @SuppressWarnings("unchecked") // Suppress warnings for Java 9
    private void save() throws IOException {
        System.out.println("Saving library to file...");
        // Set the type of the object, in this case Restaurant
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("library.xml"));
        out.writeObject(DashboardController.library);
        out.close();
        System.out.println("Library saved successfully");
    }

    /**
     * Read the restaurant object from a json file and load into
     * application
     *
     * References:
     * https://github.com/google/gson/blob/master/UserGuide.md
     * https://stackoverflow.com/questions/29319434/how-to-save-data-with-gson-in-a-json-file
     *
     * @throws IOException throws an IOException if the data cannot be read from file
     */
    @SuppressWarnings("unchecked") // Suppress warnings for Java 9
    private void load() throws IOException, ClassNotFoundException {
        System.out.println("Loading library from file...");
        // Set the type of the object, in this case Restaurant
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("library.xml"));
        DashboardController.library = (Library) is.readObject();
        is.close();
        System.out.println("Library loaded successfully");
    }



}
