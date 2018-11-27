package Controller;

import java.io.IOException;

import Server.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegisterWindowController {

	private MainController mainController;
	private Client client;
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
		String statment = "SELECT * FROM Users where login like '" + s1 + "' and pass like '" + s2 + "'";
		int temp = Integer.parseInt(client.getString(statment));
		
		if (temp != -1) {
			label.setVisible(true);
			label.setText("Rejestracja zakończona niepowodzeniem");
		}
		else if(s1.length() == 0 || s2.length() == 0)
		{
			label.setVisible(true);
			label.setText("Uzupełnij wszystkie pola");
		}
		else {
			int max = 0;
			statment = "SELECT MAX(USER_ID) FROM USERS";
			max = Integer.parseInt(client.getString(statment)) +1; 
			statment = "INSERT INTO USERS VALUES (" + max + ",'" + s1 + "' , '" + s2 + "')";
			client.getString(statment);
		
			label.setVisible(true);
			label.setText("Rejestracja zakończona sukcesem");
		}

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;

	}

	public void setClient(Client client) {
		this.client=client;
	}

}
