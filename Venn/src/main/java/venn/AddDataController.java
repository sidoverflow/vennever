package venn;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import venn.DemoController.ToggleSwitch;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
    public AnchorPane mainPane = new AnchorPane();

    @FXML
    private Label firstSetLabel = new Label();
    
    public ToggleSwitch toggle;
    
    @FXML
    private Label secondSetLabel = new Label();
    @FXML
    private Button threeCol = new Button();

    @FXML
    private Button twoCol = new Button();
    @FXML
    MenuBar menuBar = new MenuBar();
    @FXML
    public Label text = new Label();
    
    Boolean duplicateCheck;

    @FXML
	Alert a = new Alert(AlertType.NONE);
    
	private Scene firstScene;

	private Scene thirdScene;
	private DemoController firstController;
	List<String> firstDataArray = new ArrayList<String>();
	List<String> secondDataArray = new ArrayList<String>();
	List<String> intersection = new ArrayList<String>();
	private AddDataIntersectionController thirdController;

    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
  
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
        
    }
    
    public void setThirdController(AddDataIntersectionController controller) {
        thirdController = controller;
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
    public void openThirdScene(ActionEvent actionEvent) throws IOException {
    	Stage primaryStage = (Stage) done.getScene().getWindow();
        
        toggle = new ToggleSwitch();
		
        toggle.setTranslateX(910);
        toggle.setTranslateY(70);

        text.setStyle("-fx-text-fill: #ffffff");
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("Count View").otherwise("Text View"));

        thirdScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
        mainPane.getChildren().add(toggle);
        primaryStage.setScene(thirdScene);
        
    }
    @FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
		if (toggle.switchedOnProperty().toString().equals("BooleanProperty [value: false]")) {
			getVennData();
			if (!duplicateCheck) {
				firstController.inflateCircle(firstDataArray, secondDataArray, intersection);
				firstSet.clear();
				secondSet.clear();
				mainPane.getChildren().remove(toggle);
				openFirstScene(event);
			}
		}
		else {
			getVennData();
			if (!duplicateCheck) {
				List<String> intersection = new ArrayList<String>();
				for (int i = 0; i < firstDataArray.size(); i++) {
					for (int j = 0; j < secondDataArray.size(); j++) {
						if (firstDataArray.get(i).equals(secondDataArray.get(j))) {
							intersection.add(firstDataArray.get(i));
						}
					}
				}
				// remove the intersecting elements from the respective arrays
				for (int i = 0; i < intersection.size(); i++) {
					firstDataArray.remove(intersection.get(i));
					secondDataArray.remove(intersection.get(i));
				}
				firstController.inflateCircle(firstDataArray.size(), intersection.size(), secondDataArray.size());
				firstSet.clear();
				secondSet.clear();
				mainPane.getChildren().remove(toggle);
				openFirstScene(event);
			}
		}
		
		firstDataArray.clear();
		secondDataArray.clear();
		intersection.clear();
		duplicateCheck = false;
		
	}
	
   
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException{
		openFirstScene(event);
		
	}
	
	private void getVennData() throws IOException {
		
		
		
		String firstData = firstSet.getText();
		String secondData = secondSet.getText();
		
	
		Scanner scanner1 = new Scanner(firstData);
		Scanner scanner2 = new Scanner(secondData);
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
			
		scanner1.close();
		scanner2.close();
		
		
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
		

		
		
		// find the elements of the intersection set of the two user-input data sets and
				// add them to an array called intersection
		
		for (int i = 0; i < firstDataArray.size(); i++) {
			for (int j = 0; j < secondDataArray.size(); j++) {
				if (firstDataArray.get(i).equals(secondDataArray.get(j))) {
					intersection.add(firstDataArray.get(i));
				}
			}
		}

		// remove the intersecting elements from the respective arrays
		for (int i = 0; i < intersection.size(); i++) {
			firstDataArray.remove(intersection.get(i));
			secondDataArray.remove(intersection.get(i));
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