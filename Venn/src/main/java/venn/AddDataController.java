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
    
    public List<String> firstDataArray = new ArrayList<String>();
	public List<String> secondDataArray = new ArrayList<String>();
	
	
	
    void loadScene(ActionEvent event, String loc, String title) throws IOException{
    	Parent parent = FXMLLoader.load(getClass().getResource(loc));
    	Scene scene = new Scene(parent);
    	
    	//Get stage information
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setTitle(title);
    	window.setScene(scene);
    	window.show();
    }
   
	
	@FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
		// get a handle to the stage
	    Stage stage = (Stage) done.getScene().getWindow();
	    // do what you have to do
	    stage.close();
    	getVennData();
    	

	}
	@FXML
	private void backButtonAction(ActionEvent event) throws IOException{
		loadScene(event, "Demo.fxml", "Venn Builder");
	}
	
	private void getVennData() throws IOException {
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
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Demo.fxml"));
    	Parent parent = loader.load();
    	
    	DemoController controller = (DemoController) loader.getController();
    	controller.inflateCircle(firstDataArray, secondDataArray);
    	
    	Stage stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("Venn Builder");
		stage.setScene(new Scene(parent));
		stage.show();
		
		
	}
}
