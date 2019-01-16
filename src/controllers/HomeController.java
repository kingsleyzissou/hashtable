package controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

	/**
	 * Go to the search view
	 * 
	 * @throws IOException
	 */
    @FXML
    public void search() throws IOException {
        DashboardController.dbc.searchView();
    }

}
