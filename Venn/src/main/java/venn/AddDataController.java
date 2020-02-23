package venn;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddDataController {
	
	@FXML
	private Button done = new Button();
	@FXML
	private Button back = new Button();
	@FXML
    public TextArea firstSet = new TextArea();
    @FXML
    public TextArea secondSet= new TextArea();
    @FXML
    AnchorPane root = new AnchorPane();
    
    
	
	
	private Scene firstScene;
	private DemoController firstController;

    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
    
    public void setFirstController(DemoController controller) {
        firstController = controller;
    }

    public void openFirstScene(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
        firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
        
    }
    @FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
		getVennData();
		openFirstScene(event);
		
	}
	
   
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException{
		openFirstScene(event);
		
	}
	
	private void getVennData() throws IOException {
		
		List<String> firstDataArray = new ArrayList<String>();
		List<String> secondDataArray = new ArrayList<String>();
		String firstData = firstSet.getText();
		String secondData = secondSet.getText();
		
	
		Scanner scanner1 = new Scanner(firstData);
		Scanner scanner2 = new Scanner(secondData);
		while (scanner1.hasNextLine()) {
			String line = scanner1.nextLine();
			firstDataArray.add(line);
		}
		while (scanner2.hasNextLine()) {
			String line = scanner2.nextLine();
			secondDataArray.add(line);
		}
		scanner1.close();
		scanner2.close();
		
		
		firstController.inflateCircle(firstDataArray, secondDataArray);
		
	
		
	}
}
