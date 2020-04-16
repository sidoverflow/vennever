package venn;



import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
	 

public class MainView extends Application {
	double xOffset = 0;
	double yOffset = 0;
	public static void main(String[] args) {
       		Application.launch(args);
    	}
	 
// JavaFX entry point
	@Override
	public void start(Stage primaryStage) {
		
		try { 
			// getting loader and a pane for the first scene. 
	        // loader will then give a possibility to get related controller
			
			
			
	        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("Demo.fxml"));
	        Parent root = firstPaneLoader.load();
	        Scene firstScene = new Scene(root);

	        // getting loader and a pane for the second scene
	        FXMLLoader secondPaneLoader = new FXMLLoader(getClass().getResource("AddData.fxml"));
	        Parent secondPane = secondPaneLoader.load();
	        Scene secondScene = new Scene(secondPane);
	        
	        // getting loader and a pane for the third scene
	        FXMLLoader thirdPaneLoader = new FXMLLoader(getClass().getResource("AddDataIntersection.fxml"));
	        Parent thirdPane = thirdPaneLoader.load();
	        Scene thirdScene = new Scene(thirdPane);
	        
	     // getting loader and a pane for the third scene
	        FXMLLoader fourthPaneLoader = new FXMLLoader(getClass().getResource("VennTest.fxml"));
	        Parent fourthPane = fourthPaneLoader.load();
	        Scene fourthScene = new Scene(fourthPane);

	        // injecting second and fourth scene into the controller of the first scene
	        DemoController firstPaneController = (DemoController) firstPaneLoader.getController();
	        firstPaneController.setSecondScene(secondScene);
	        firstPaneController.setFourthScene(fourthScene);
	        

	        // injecting first and third scene into the controller of the second scene
	        AddDataController secondPaneController = (AddDataController) secondPaneLoader.getController();
	        secondPaneController.setFirstScene(firstScene);
	        secondPaneController.setThirdScene(thirdScene);
	        secondPaneController.setFirstController(firstPaneController);
	        
	        // injecting first and second scene into the controller of the third scene
	        AddDataIntersectionController thirdPaneController = (AddDataIntersectionController) thirdPaneLoader.getController();
	        thirdPaneController.setSecondScene(secondScene);
	        thirdPaneController.setFirstScene(firstScene);
	        thirdPaneController.setFirstController(firstPaneController);
	        
	     // injecting first scene into the controller of the third scene
	        VennTestController fourthPaneController = (VennTestController) fourthPaneLoader.getController();
	        fourthPaneController.setFirstScene(firstScene);
	        fourthPaneController.setFirstController(firstPaneController);
	        
	        firstPaneController.setSecondController(secondPaneController);
	        secondPaneController.setThirdController(thirdPaneController);
	        
	        
//	        primaryStage = new Stage(StageStyle.DECORATED);
	        //grab your root here
	        firstPaneController.menuBar.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });

	        //move around here
	        firstPaneController.menuBar.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });
	        
	        secondPaneController.menuBar.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });

	        //move around here
	        secondPaneController.menuBar.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });
	        
	        thirdPaneController.menuBar.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });

	        //move around here
	        thirdPaneController.menuBar.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });
	        
	        fourthPaneController.menuBar.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });

	        //move around here
	        fourthPaneController.menuBar.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });
			

	      
	        
	        
	        firstScene.setFill(Color.TRANSPARENT);
	        primaryStage.setTitle("VennEver");
			primaryStage.setScene(firstScene);
			firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
	        primaryStage.initStyle(StageStyle.TRANSPARENT);
	        
	        
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}       
    }
	
	
	
	
}
