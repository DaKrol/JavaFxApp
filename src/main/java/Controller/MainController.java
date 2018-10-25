package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {

	@FXML
	private StackPane mainStackPane;
	

	@FXML
	public void initialize() {
		loadPaneWindow();
	}
	public void loadPaneWindow()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/PaneWindow.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PaneWindowController paneController = loader.getController();
		paneController.setMainController(this);
		setScreen(pane);
	}
	public void setScreen(Pane pane)
	{
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	
}
