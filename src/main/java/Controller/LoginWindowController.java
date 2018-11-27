package Controller;

import java.io.IOException;

import Server.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class LoginWindowController {

	private MainController mainController;
	private Client client;
	@FXML
	private ComboBox<String> choiceBox;
	@FXML
	private Button confirm;
	@FXML
	private Button log;

	@FXML
	public void choice() {
		choiceBox.getItems().clear();
		int nrOfSurveys = Baza.executeStatment("SELECT MAX(id) FROM ankieta");
		for (int i = 0; i < nrOfSurveys; i++) {
			String s = Baza.executeStatmentString("SELECT nazwa from ANKIETA where id =" + (i+1));
			choiceBox.getItems().add(s);
		}
	}

	@FXML
	public void confirmChoice() {
		if (choiceBox.getValue() != null) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/fxml/SurveyWindow.fxml"));

			AnchorPane pane = null;

			try {
				pane = loader.load();
			} catch (IOException e) {

				e.printStackTrace();
			}
			String pytanie;
			String a, b, c, d;
			int idAnkiety = 0, liczbaPytan;
			String statment = "SELECT ID FROM ANKIETA WHERE NAZWA = '"+ choiceBox.getValue() +"'";
			idAnkiety = Integer.parseInt(client.getString(statment));
			//idAnkiety = Baza.executeStatment("SELECT ID FROM ANKIETA WHERE NAZWA = '"+ choiceBox.getValue() +"'");
			pytanie = client.getString("SELECT PYTANIE FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
		//	pytanie =  Baza.executeStatmentString("SELECT PYTANIE FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
			liczbaPytan = Baza.executeStatment("SELECT MAX(ID_PYTANIA_ANK) FROM PYTANIA WHERE ID_ANKIETY = '"+ idAnkiety +"'");

			SurveyWindowController surController = loader.getController();
			
			surController.setIdAnkiety(idAnkiety);
			surController.setLabel(pytanie);	
			surController.setLiczbaPytan(liczbaPytan);
			
			a = Baza.executeStatmentString("SELECT ODPA FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
			b = Baza.executeStatmentString("SELECT ODPB FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
			c = Baza.executeStatmentString("SELECT ODPC FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
			d = Baza.executeStatmentString("SELECT ODPD FROM PYTANIA WHERE ID_PYTANIA_ANK =" + 1 + " and ID_ANKIETY = " +idAnkiety);
			
			
			if (a != null)
				surController.setChooseAnswer(a);
			if (b != null)
				surController.setChooseAnswer(b);
			if (c != null)
				surController.setChooseAnswer(c);
			if (d != null)
				surController.setChooseAnswer(d);
			
			surController.setMainController(mainController);
			mainController.setScreen(pane);

		}

	}

	@FXML
	public void logOut() {
		mainController.loadPaneWindow();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;

	}

	public void setClient(Client client) {
		this.client=client;
	}
}
