package Controller;

import java.io.IOException;

import Data.Users;
import Server.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneWindowController {
	
	private Client client;
	private MainController mainController;
	
	@FXML
	private Button zaloguj;
	@FXML
	private TextField login;
	@FXML
	private PasswordField passField;
	@FXML
	private Button register;
	@FXML 
	private Label lab;
	
	@FXML
	void initialize()  {
		
	}


	@FXML
	public void onMouseClickedZaloguj() 
	{
		
		String s1 = login.getText();
		String s2 = passField.getText();
		int temp = Integer.parseInt(client.getString("SELECT * FROM Users where login like '" + s1 + "' and pass like '" + s2 + "'"));
		System.out.println(client.executeQuerry("SELECT * FROM Users where login like '" + s1 + "' and pass like '" + s2 + "'"));
		if (temp != -1) {
			mainController.loadLoginWindow();
		}
		else
		{
			lab.setVisible(true);
		}
	
	}
	
	@FXML 
	public void registerButton()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/RegisterWindow.fxml"));
		
		Pane pane = null;
	
		try {
			 pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RegisterWindowController regController = loader.getController();
		regController.setMainController(mainController);
		regController.setClient(client);
		mainController.setScreen(pane);
	}

	public void setMainController(MainController mainController) {
		this.mainController=mainController;
		
	}


	public void setClient(Client client) {
		this.client=client;
	}
	
	
}
