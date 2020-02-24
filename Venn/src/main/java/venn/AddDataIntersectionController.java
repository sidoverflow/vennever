package venn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddDataIntersectionController {

    @FXML
    private TextArea thirdSet;

    @FXML
    private Label firstSetLabel;

    @FXML
    private Label secondSetLabel;

    @FXML
    private Button done;

    @FXML
    private Button back;

    @FXML
    private TextArea firstSet;

    @FXML
    private Button threeCol;

    @FXML
    private Button twoCol;

    @FXML
    private TextArea secondSet;

    @FXML
    private Label intersectionSetLabel;

    private Scene firstScene;
    private Scene secondScene;

	private DemoController firstController;
	
	

    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    public void setFirstController(DemoController controller) {
        firstController = controller;
    }
    @FXML
    public void openFirstScene(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
        firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
        
    }
    @FXML
    public void openSecondScene(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        
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
		List<String> thirdDataArray = new ArrayList<String>();
		
		String firstData = firstSet.getText();
		String secondData = secondSet.getText();
		String thirdData = thirdSet.getText();
		
	
		Scanner scanner1 = new Scanner(firstData);
		Scanner scanner2 = new Scanner(secondData);
		Scanner scanner3 = new Scanner(thirdData);
		while (scanner1.hasNextLine()) {
			String line = scanner1.nextLine();
			firstDataArray.add(line);
		}
		while (scanner2.hasNextLine()) {
			String line = scanner2.nextLine();
			secondDataArray.add(line);
		}
		while (scanner3.hasNextLine()) {
			String line = scanner3.nextLine();
			thirdDataArray.add(line);
		}
		scanner1.close();
		scanner2.close();
		scanner3.close();
		
		firstController.inflateCircle(firstDataArray, secondDataArray, thirdDataArray);
		
	
		
	}
	
}


