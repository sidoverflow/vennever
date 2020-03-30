package venn;



import org.aerofx.AeroFX;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
	 

public class Main extends Application {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("java.classpath"));
        launch(args);
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

	        // injecting second scene into the controller of the first scene
	        DemoController firstPaneController = (DemoController) firstPaneLoader.getController();
	        firstPaneController.setSecondScene(secondScene);

	        // injecting first scene into the controller of the second scene
	        AddDataController secondPaneController = (AddDataController) secondPaneLoader.getController();
	        secondPaneController.setFirstScene(firstScene);
	        secondPaneController.setThirdScene(thirdScene);
	        secondPaneController.setFirstController(firstPaneController);
	        
	        // injecting second scene into the controller of the first scene
	        AddDataIntersectionController thirdPaneController = (AddDataIntersectionController) thirdPaneLoader.getController();
	        thirdPaneController.setSecondScene(secondScene);
	        thirdPaneController.setFirstScene(firstScene);
	        thirdPaneController.setFirstController(firstPaneController);
	        
	        
	        primaryStage = new Stage(StageStyle.DECORATED);
//	        //grab your root here
//	        root.setOnMousePressed(event -> {
//	            xOffset = event.getSceneX();
//	            yOffset = event.getSceneY();
//	        });
//
//	        //move around here
//	        root.setOnMouseDragged(event -> {
//	            primaryStage.setX(event.getScreenX() - xOffset);
//	            primaryStage.setY(event.getScreenY() - yOffset);
//	        });
			

	        //set transparent
	        
	        
	        
	        firstScene.setFill(Color.TRANSPARENT);
	        primaryStage.setTitle("VennEver");
			primaryStage.setScene(firstScene);
			firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
//	        primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}       
    }
	
	
	
	
}
