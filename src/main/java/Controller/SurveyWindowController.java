package Controller;

import Server.Baza;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SurveyWindowController {
	private MainController mainController;
	private int idAnkiety;
	private int liczbaPytan;
	private int obecnePytanie = 1;
	@FXML
	private Label question;
	@FXML
	private Label info;
	@FXML
	private ChoiceBox chooseAnswer;
	@FXML
	private Button next;

	@FXML
	public void nextQuestion() {
		if (chooseAnswer.getValue() == null) {
			info.setVisible(true);
		}
		else
		{
			
		if (next.getText() == "Cofnij") {
			mainController.loadLoginWindow();
		} else if (liczbaPytan == obecnePytanie) {
			info.setVisible(false);
			question.setText("Koniec ankiety");
			chooseAnswer.setVisible(false);
			next.setText("Cofnij");
		} else {
		
				info.setVisible(false);
				chooseAnswer.getItems().clear();
				String pytanie = Baza.executeStatmentString("SELECT PYTANIE FROM PYTANIA WHERE ID_PYTANIA_ANK ="
						+ ++obecnePytanie + " and ID_ANKIETY = " + idAnkiety);
				String a, b, c, d;

				if (pytanie != null)
					question.setText(pytanie);
				a = Baza.executeStatmentString("SELECT ODPA FROM PYTANIA WHERE ID_PYTANIA_ANK =" + obecnePytanie
						+ " and ID_ANKIETY = " + idAnkiety);
				b = Baza.executeStatmentString("SELECT ODPB FROM PYTANIA WHERE ID_PYTANIA_ANK =" + obecnePytanie
						+ " and ID_ANKIETY = " + idAnkiety);
				c = Baza.executeStatmentString("SELECT ODPC FROM PYTANIA WHERE ID_PYTANIA_ANK =" + obecnePytanie
						+ " and ID_ANKIETY = " + idAnkiety);
				d = Baza.executeStatmentString("SELECT ODPD FROM PYTANIA WHERE ID_PYTANIA_ANK =" + obecnePytanie
						+ " and ID_ANKIETY = " + idAnkiety);

				if (a != null)
					setChooseAnswer(a);
				if (b != null)
					setChooseAnswer(b);
				if (c != null)
					setChooseAnswer(c);
				if (d != null)
					setChooseAnswer(d);
			}
		}
	}

	public void setChooseAnswer(String s) {
		this.chooseAnswer.getItems().add(s);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;

	}

	public void setIdAnkiety(int idAnkiety) {
		this.idAnkiety = idAnkiety;
	}

	public void setLiczbaPytan(int liczbaPytan) {
		this.liczbaPytan = liczbaPytan;
	}

	public void setLabel(String s) {
		question.setText(s);
	}
}
