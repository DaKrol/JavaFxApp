package Controller;

import java.io.IOException;

import Database.Baza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PaneWindowController {
	

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
		if (Baza.checkLoginPass(s1, s2) != 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/fxml/LoginWindow.fxml"));
			
			Pane pane = null;
		
			try {
				 pane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainController.setScreen(pane);
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
		mainController.setScreen(pane);
	}

	public void setMainController(MainController mainController) {
		this.mainController=mainController;
		
	}
	
	
}
