package venn;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
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

public class DemoController {
	
	@FXML
    private Circle pink = new Circle();
	@FXML
	private Circle blue = new Circle();
    @FXML
    public TextArea firstText = new TextArea();
    @FXML
    public TextArea secondText = new TextArea();
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException{
    	
    	Parent addDataViewParent = FXMLLoader.load(getClass().getResource("AddData.fxml"));
    	Scene addDataViewScene = new Scene(addDataViewParent);
    	
    	//Get stage information
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(addDataViewScene);
    	window.show();

    }
    
    
   
    
    
    
    
    
    
}
