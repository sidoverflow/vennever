package venn;

import java.awt.Dialog;
import java.awt.TextField;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DemoController {
	
	@FXML
    private Circle pink = new Circle();
	@FXML
	private Circle blue = new Circle();
    @FXML
    public Text firstText = new Text();
    @FXML
    public Text secondText = new Text();
    
    @FXML
    public void handleButtonAction() {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddData.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1));  
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
    	
    	

    }
    
    public void displayText(List<String> firstArray, List<String> secondArray) {
//    	String temp1 = "";
//		String temp2 = "";
//		for (String each: firstArray) {
//			temp1 += each;
//		}
//		firstText.setText(temp1);
//		for (String each: secondArray) {
//			temp2 += each;
//		}
//		secondText.setText(temp2);
    	firstText.setText(firstArray.get(0));
    	secondText.setText(secondArray.get(0));
    	
    }
    
   
    
    
    
    
    
    
}
