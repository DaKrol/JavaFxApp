package Test.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application
{

	public static void main( String[] args )
    {
     	launch();
     	
    
    }
    
	@Override
	public void start(Stage stg) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/MainScreen.fxml"));
		StackPane pane = loader.load();
	
		Scene scene = new Scene(pane);
	
		
		stg.setScene(scene);
		stg.setWidth(600);
		stg.setHeight(400);
		stg.setTitle("Okno logowania");
	//	stg.setResizable(false);
		stg.show();
	}
	
	

	
	
}
