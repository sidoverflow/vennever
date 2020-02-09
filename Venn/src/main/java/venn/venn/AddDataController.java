package venn;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    
    DemoController control = new DemoController();
	
	
	@FXML
	private void doneButtonAction(){
	    // get a handle to the stage
	    Stage stage = (Stage) done.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	    getVennData();
	}
	
	private void getVennData() {
		String firstData = firstSet.getText();
		String secondData = secondSet.getText();
		
		List<String> firstDataArray = new ArrayList<String>();
		List<String> secondDataArray = new ArrayList<String>();
		
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
		
		
//		System.out.println(firstDataArray.get(0));
//		System.out.println(secondDataArray.get(0));
		
		
		
		control.displayText(firstDataArray, secondDataArray);
		
		
		
		
	}
}
