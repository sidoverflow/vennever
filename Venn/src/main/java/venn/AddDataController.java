package venn;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public AnchorPane root = new AnchorPane();

    @FXML
    private Label firstSetLabel = new Label();

    @FXML
    private Label secondSetLabel = new Label();
    @FXML
    private Button threeCol = new Button();

    @FXML
    private Button twoCol = new Button();
    @FXML
    MenuBar menuBar = new MenuBar();
	
	
	private Scene firstScene;

	private Scene thirdScene;
	private DemoController firstController;
	
	

    public void setFirstScene(Scene scene) {
        firstScene = scene;
    }
  
    public void setThirdScene(Scene scene) {
        thirdScene = scene;
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
    public void openThirdScene(ActionEvent actionEvent) throws IOException {
    	Stage primaryStage = (Stage) done.getScene().getWindow();
        primaryStage.setScene(thirdScene);
        thirdScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
        
    }
    @FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
		getVennData();
		firstSet.clear();
		secondSet.clear();
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