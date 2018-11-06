package Controller;

import java.io.IOException;

import Database.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegisterWindowController {

	private MainController mainController;

	@FXML
	private TextField loginR;
	@FXML
	private PasswordField passFieldR;
	@FXML
	private Label label;

	@FXML
	public void back() {
		mainController.loadPaneWindow();
	}

	@FXML
	public void register() {
		String s1 = loginR.getText();
		String s2 = passFieldR.getText();
		if (Baza.checkLoginPass(s1, s2) != 0) {
			
			label.setVisible(true);
			label.setText("Rejestracja zakończona niepowodzeniem");
		}
		else if(s1.length() == 0 || s2.length() == 0)
		{
			label.setVisible(true);
			label.setText("Uzupełnij wszystkie pola");
		}
		else {
			
			Baza.addRow(s1,s2);
			label.setVisible(true);
			label.setText("Rejestracja zakończona sukcesem");
		}

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;

	}

}
