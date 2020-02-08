package venn;

import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
	 

public class Main extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }
	 
// JavaFX entry point
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Demo.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}       
    }
	
	
	
>>>>>>> refs/heads/sid_develop
//areeba edit
	
	
}

