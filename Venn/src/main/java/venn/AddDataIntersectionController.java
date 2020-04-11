package venn;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import venn.DemoController.ToggleSwitch;

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
    public AnchorPane mainPane = new AnchorPane();

    @FXML
    private Button threeCol;

    @FXML
    private Button twoCol;

    @FXML
    private TextArea secondSet;

    @FXML
    private Label intersectionSetLabel;
    
    public ToggleSwitch toggle;
    public Label text = new Label();
    @FXML
    MenuBar menuBar = new MenuBar();

    private Scene firstScene;
    private Scene secondScene;

	private DemoController firstController;
	List<String> firstDataArray = new ArrayList<String>();
	List<String> secondDataArray = new ArrayList<String>();
	List<String> thirdDataArray = new ArrayList<String>();
	
	Boolean duplicateCheck;

    @FXML
	Alert a = new Alert(AlertType.NONE);
	

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
        mainPane.getChildren().remove(toggle);
        firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
        
    }
    @FXML
    public void openSecondScene(ActionEvent actionEvent) throws IOException {
    	Stage primaryStage = (Stage) done.getScene().getWindow();
		secondScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
		
		toggle = new ToggleSwitch();
		
        toggle.setTranslateX(910);
        toggle.setTranslateY(70);

        text.setStyle("-fx-text-fill: #ffffff");
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("Count View").otherwise("Text View"));

        mainPane.getChildren().add(toggle);
		primaryStage.setScene(secondScene);
    	
        
    }
    
    @FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
    	if (toggle.switchedOnProperty().toString().equals("BooleanProperty [value: false]")) {
			getVennData();
			if (!duplicateCheck) {
				firstController.inflateCircle(firstDataArray, secondDataArray, thirdDataArray);
				firstSet.clear();
				secondSet.clear();
				mainPane.getChildren().remove(toggle);
				openFirstScene(event);
			}
		}
		else {
			getVennData();
			if (!duplicateCheck) {
				firstController.inflateCircle(firstDataArray.size(), thirdDataArray.size(), secondDataArray.size());
				firstSet.clear();
				secondSet.clear();
				mainPane.getChildren().remove(toggle);
				openFirstScene(event);
			}
		}
		
		firstDataArray.clear();
		secondDataArray.clear();
		thirdDataArray.clear();
		duplicateCheck = false;
		
	}
	
   
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException{
		openFirstScene(event);
		
	}
	
	private void getVennData() throws IOException {
		
		
		
		String firstData = firstSet.getText();
		String secondData = secondSet.getText();
		String thirdData = thirdSet.getText();
		
	
		Scanner scanner1 = new Scanner(firstData);
		Scanner scanner2 = new Scanner(secondData);
		Scanner scanner3 = new Scanner(thirdData);
		while (scanner1.hasNextLine()) {
			String line = scanner1.nextLine();
			if (!line.equals("")) {
				firstDataArray.add(line);
			}
		}
		while (scanner2.hasNextLine()) {
			String line = scanner2.nextLine();
			if (!line.equals("")) {	
				secondDataArray.add(line);
		
			}
		}
		while (scanner3.hasNextLine()) {
			String line = scanner3.nextLine();
			if (!line.equals("")) {	
				thirdDataArray.add(line);
			}
		}
		scanner1.close();
		scanner2.close();
		scanner3.close();
		
		for (int i = 0; i < firstDataArray.size(); i++) {
			for (int j = 0; j < firstDataArray.size(); j++) {
				if (firstDataArray.get(i).equals(firstDataArray.get(j)) && i != j) {
					DialogPane dialogPane = a.getDialogPane();
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found in the first set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
		for (int i = 0; i < secondDataArray.size(); i++) {
			for (int j = 0; j < secondDataArray.size(); j++) {
				if (secondDataArray.get(i).equals(secondDataArray.get(j)) && i != j) {
					DialogPane dialogPane = a.getDialogPane();
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found in the second set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
		for (int i = 0; i < thirdDataArray.size(); i++) {
			for (int j = 0; j < thirdDataArray.size(); j++) {
				if (thirdDataArray.get(i).equals(thirdDataArray.get(j)) && i != j) {
					DialogPane dialogPane = a.getDialogPane();
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found in the intersection set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
		for (int i = 0; i < firstDataArray.size(); i++) {
			for (int j = 0; j < secondDataArray.size(); j++) {
				if (firstDataArray.get(i).equals(secondDataArray.get(j))) {
					for (int k = 0; k < thirdDataArray.size(); k++) {
						if (firstDataArray.get(i).equals(thirdDataArray.get(k))) {
							DialogPane dialogPane = a.getDialogPane();
							a.setWidth(500);
							dialogPane.getStylesheets()
									.add(getClass().getResource("editable-text.css").toExternalForm());
							dialogPane.getStyleClass().add("alert-pane");
							a.setAlertType(AlertType.ERROR);
							a.setHeaderText("Duplicate Warning");
							a.setContentText("Duplicate entries found between the first, second and intersection set.");
							a.show();
							duplicateCheck = true;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < firstDataArray.size(); i++) {
			for (int j = 0; j < secondDataArray.size(); j++) {
				if (firstDataArray.get(i).equals(secondDataArray.get(j))) {
					DialogPane dialogPane = a.getDialogPane();
					a.setWidth(500);
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found between the first and second set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
		for (int i = 0; i < firstDataArray.size(); i++) {
			for (int j = 0; j < thirdDataArray.size(); j++) {
				if (firstDataArray.get(i).equals(thirdDataArray.get(j))) {
					DialogPane dialogPane = a.getDialogPane();
					a.setWidth(500);
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found between the first and intersection set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
		for (int i = 0; i < secondDataArray.size(); i++) {
			for (int j = 0; j < thirdDataArray.size(); j++) {
				if (secondDataArray.get(i).equals(thirdDataArray.get(j))) {
					DialogPane dialogPane = a.getDialogPane();
					a.setWidth(500);
					dialogPane.getStylesheets().add(
					   getClass().getResource("editable-text.css").toExternalForm());
					dialogPane.getStyleClass().add("alert-pane");
					a.setAlertType(AlertType.ERROR);
					a.setHeaderText("Duplicate Warning");
					a.setContentText("Duplicate entries found between the second and intersection set.");
					a.show();
					duplicateCheck = true;
				}
			}
		}
		
	
		
	}
	public void openBrowser(ActionEvent actionEvent) throws Exception {

		try {
		    Desktop.getDesktop().browse(new URL("https://www.github.com/sidoverflow/VennProject").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}

    }
	public void quitButton(ActionEvent actionEvent) {
		Stage stage = (Stage) done.getScene().getWindow();
	    stage.close();
	}
	
}


