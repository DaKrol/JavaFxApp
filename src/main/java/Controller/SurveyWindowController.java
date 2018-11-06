package Controller;

import Database.Baza;
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

		if(next.getText() == "Cofnij")
		{
			mainController.loadLoginWindow();
		}
		else if (liczbaPytan  == obecnePytanie) {
			info.setVisible(false);
			question.setText("Koniec ankiety");
			chooseAnswer.setVisible(false);
			next.setText("Cofnij");
		} else {
			if (chooseAnswer.getValue() == null)
			{
				info.setVisible(true);
			}
			else {
				info.setVisible(false);
				chooseAnswer.getItems().clear();
				String pytanie = Baza.getQuestion(idAnkiety, ++obecnePytanie);
				String a, b, c, d;

				if (pytanie != null)
					question.setText(pytanie);
				a = Baza.getAnswer(idAnkiety, obecnePytanie, "ODPA");
				b = Baza.getAnswer(idAnkiety, obecnePytanie, "ODPB");
				c = Baza.getAnswer(idAnkiety, obecnePytanie, "ODPC");
				d = Baza.getAnswer(idAnkiety, obecnePytanie, "ODPD");
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
