package Controller;

import java.io.IOException;

import Database.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class LoginWindowController {

	private MainController mainController;

	@FXML
	private ComboBox<String> choiceBox;
	@FXML
	private Button confirm;
	@FXML
	private Button log;

	@FXML
	public void choice() {
		int nrOfSurveys = Baza.getNumberOfSurveys();
		for (int i = 0; i < nrOfSurveys; i++) {
			choiceBox.getItems().add(Baza.getData(i + 1));
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
			int idAnkiety = 0;
			idAnkiety = Baza.getIdAnkiety(choiceBox.getValue());
			pytanie = Baza.getQuestion(idAnkiety, 1);

			SurveyWindowController surController = loader.getController();
			
			surController.setIdAnkiety(idAnkiety);
			surController.setLabel(pytanie);	
			surController.setLiczbaPytan(Baza.getLiczbaPytan(idAnkiety));
			a = Baza.getAnswer(idAnkiety, 1, "ODPA");
			b = Baza.getAnswer(idAnkiety, 1, "ODPB");
			c = Baza.getAnswer(idAnkiety, 1, "ODPC");
			d = Baza.getAnswer(idAnkiety, 1, "ODPD");
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
}
