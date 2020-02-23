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
import javafx.stage.StageStyle;
	 

public class Main extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }
	 
// JavaFX entry point
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// getting loader and a pane for the first scene. 
	        // loader will then give a possibility to get related controller
	        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Demo.fxml"));
	        Parent firstPane = firstPaneLoader.load();
	        Scene firstScene = new Scene(firstPane);

	        // getting loader and a pane for the second scene
	        FXMLLoader secondPaneLoader = new FXMLLoader(getClass().getResource("AddData.fxml"));
	        Parent secondPane = secondPaneLoader.load();
	        Scene secondScene = new Scene(secondPane);

	        // injecting second scene into the controller of the first scene
	        DemoController firstPaneController = (DemoController) firstPaneLoader.getController();
	        firstPaneController.setSecondScene(secondScene);

	        // injecting first scene into the controller of the second scene
	        AddDataController secondPaneController = (AddDataController) secondPaneLoader.getController();
	        secondPaneController.setFirstScene(firstScene);
	        secondPaneController.setFirstController(firstPaneController);
	        

	        primaryStage.setTitle("Venn Builder");
	        
	        
	        
			primaryStage = new Stage(StageStyle.DECORATED);
			primaryStage.setScene(firstScene);
			firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}       
    }
	
	
	
	
}

