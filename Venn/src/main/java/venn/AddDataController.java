package venn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AddDataController {
	
	@FXML
	private Button done = new Button();
	@FXML
    public TextArea firstSet = new TextArea();
    @FXML
    public TextArea secondSet= new TextArea();
    @FXML
    AnchorPane root;
    
    public List<String> firstDataArray = new ArrayList<String>();
	public List<String> secondDataArray = new ArrayList<String>();
    

	
	
	@FXML
	private void doneButtonAction(ActionEvent event) throws IOException{
		//Get stage information
		Parent homeParent = FXMLLoader.load(getClass().getResource("Demo.fxml"));
    	Scene addDataViewScene = new Scene(homeParent);
    	
    	//Get stage information
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(addDataViewScene);
    	window.show();
    	
    	getVennData();

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
		
		
		String a = firstDataArray.get(0);
		String b = secondDataArray.get(0);
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Demo.fxml"));
		AnchorPane pane = loader.load();
		DemoController demoController = loader.getController();
	    

	    demoController.firstText.setText(a);
	    demoController.firstText.setText(b);
	    
	    root.getChildren().setAll(pane);
		
	}
}
