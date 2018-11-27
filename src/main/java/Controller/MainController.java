package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {
	private Client client;
	
	@FXML
	private StackPane mainStackPane;
	

	@FXML
	public void initialize() {
		loadPaneWindow();
	}
	
	{
		client = new Client();
		try {
			client.Connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadPaneWindow()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/PaneWindow.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PaneWindowController paneController = loader.getController();
		paneController.setMainController(this);
		paneController.setClient(client);
		setScreen(pane);
	}
	public void loadLoginWindow()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/LoginWindow.fxml"));
		
		BorderPane pane = null;
	
		try {
			 pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginWindowController logController = loader.getController();
		logController.setMainController(this);
		logController.setClient(client);
		setScreen(pane);
	
	}

	public void setScreen(BorderPane pane)
	{
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	public void setScreen(Pane pane)
	{
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	public void setScreen(AnchorPane pane)
	{
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
}
