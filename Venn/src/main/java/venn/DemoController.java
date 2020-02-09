package venn;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DemoController {
	
	@FXML
    public Circle pink = new Circle();
	@FXML
	public Circle blue = new Circle();
    @FXML
    public TextField firstText = new TextField();
    @FXML
    public TextField secondText = new TextField();
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException{
   
    	loadScene(event, "AddData.fxml", "Add Data");

    }
    
    void loadScene(ActionEvent event, String loc, String title) throws IOException{
//    	try {
//    		Parent parent = FXMLLoader.load(getClass().getResource(loc));
//    		Stage stage = new Stage(StageStyle.DECORATED);
//    		stage.setTitle(title);
//    		stage.setScene(new Scene(parent));
//    		stage.show();
//    		
//    	} catch (IOException ex) {
//    		Logger.getLogger(DemoController.class.getName()).log(Level.SEVERE, null, ex);    		
//    	}
    	
    	Parent parent = FXMLLoader.load(getClass().getResource(loc));
    	Scene scene = new Scene(parent);
    	
    	//Get stage information
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setTitle(title);
    	window.setScene(scene);
    	window.show();
    }
    
    public void inflateCircle(List<String> first, List<String> second) {
    	firstText.setText(first.get(0));
    	secondText.setText(second.get(0));
    }
    
    
   
    
    
    
    
    
    
}
