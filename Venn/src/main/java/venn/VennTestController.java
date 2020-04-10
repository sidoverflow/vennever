package venn;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import venn.DemoController.EditableLabel;
import venn.DemoController.EditableLabel.Delta;

public class VennTestController {


    @FXML
    private Button editor;

    @FXML
    public AnchorPane canvas;

    @FXML
    private Circle lCircle;

    @FXML
    private Circle rCircle;

    @FXML
    private Button done;

    @FXML
    private Button upload;
    @FXML
    private Label infoLabel1;

    @FXML
    private Label tagsLabel;

    @FXML
    private Label infoLabel2;
    @FXML
    MenuBar menuBar = new MenuBar();
    
    public String[] leftCorrect;
    public String[] intersectionCorrect;
    public String[] rightCorrect;
    
    private DemoController firstController;
    
    private Scene firstScene;

	public void setFirstScene(Scene scene) {
		firstScene = scene;
	}
	
	public void setFirstController(DemoController controller) {
        firstController = controller;
    }
	
	public void openFirstScene(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) lCircle).getScene().getWindow();
		firstScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
		primaryStage.setScene(firstScene);
	}

    @FXML
    void doneButtonAction(ActionEvent event) {
    	List leftBoxes = new ArrayList<Node>();
		List intersectionBoxes = new ArrayList<Node>();
		List rightBoxes = new ArrayList<Node>();
		
		
		ObservableList<Node> boxes = canvas.getChildren();
		for (int i = 0 ; i < boxes.size(); i++) {
			
			
			if (boxes.get(i) != lCircle && boxes.get(i) != rCircle ) {
				if (boxes.get(i).getLayoutX() <= 196 && boxes.get(i).getLayoutX() >= 31 && boxes.get(i).getLayoutY() >= 98 && boxes.get(i).getLayoutY() <= 430) {
					leftBoxes.add(boxes.get(i));
				}
				else if (boxes.get(i).getLayoutX() <= 344 && boxes.get(i).getLayoutX() >= 261 && boxes.get(i).getLayoutY() <= 387 && boxes.get(i).getLayoutY() >= 140) {
					intersectionBoxes.add(boxes.get(i));
				}
				else if (boxes.get(i).getLayoutX() <= 566 && boxes.get(i).getLayoutX() >= 389 && boxes.get(i).getLayoutY() >= 98 && boxes.get(i).getLayoutY() <= 430) {
					rightBoxes.add(boxes.get(i));
				}
			}
		}
		
		String[] leftBoxesString = new String[leftBoxes.size()];
		String[] intersectionBoxesString = new String[intersectionBoxes.size()];
		String[] rightBoxesString = new String[rightBoxes.size()];
		
		for (int i = 0; i < leftBoxes.size(); i++) {
			int startI = leftBoxes.get(i).toString().indexOf("'");
			int lastI = leftBoxes.get(i).toString().lastIndexOf("'");
			leftBoxesString[i] = leftBoxes.get(i).toString().substring(startI + 1, lastI);
		}
		for (int i = 0; i < intersectionBoxes.size(); i++) {
			int startI = intersectionBoxes.get(i).toString().indexOf("'");
			int lastI = intersectionBoxes.get(i).toString().lastIndexOf("'");
			intersectionBoxesString[i] = intersectionBoxes.get(i).toString().substring(startI + 1, lastI);
		}
		for (int i = 0; i < rightBoxes.size(); i++) {
			int startI = rightBoxes.get(i).toString().indexOf("'");
			int lastI = rightBoxes.get(i).toString().lastIndexOf("'");
			rightBoxesString[i] = rightBoxes.get(i).toString().substring(startI + 1, lastI);
		}
		
		boolean check = false;
		
		if (Arrays.asList(intersectionCorrect).containsAll(Arrays.asList(intersectionBoxesString)) && intersectionCorrect.length == intersectionBoxesString.length) {
			if ((Arrays.asList(leftCorrect).containsAll(Arrays.asList(leftBoxesString)) && Arrays.asList(rightCorrect).containsAll(Arrays.asList(rightBoxesString)) && leftCorrect.length == leftBoxesString.length && rightCorrect.length == rightBoxesString.length) 
					|| (Arrays.asList(leftCorrect).containsAll(Arrays.asList(rightBoxesString)) && Arrays.asList(rightCorrect).containsAll(Arrays.asList(leftBoxesString)) && leftCorrect.length == rightBoxesString.length && rightCorrect.length == leftBoxesString.length) ) {
				check = true;
			}
		}
		
		if (check) {
	        try {
	        	
	        	FXMLLoader fifthPaneLoader = new FXMLLoader();
	        	fifthPaneLoader.setLocation(getClass().getResource("/venn/VenTestResults.fxml"));
		        Parent fifthPane = fifthPaneLoader.load();
		        Scene fifthScene = new Scene(fifthPane);
		        
		       
		        VennTestResultsController fifthController = (VennTestResultsController) fifthPaneLoader.getController();
		        fifthController.setFourthController(this);
		        fifthController.setFirstController(firstController);
		        fifthScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
	            Stage stage = new Stage();
	            stage.setTitle("VennTest Results");
	            stage.setScene(fifthScene);
	            stage.initStyle(StageStyle.TRANSPARENT);
	            
	            fifthController.showCorrectView();
	            
	            fifthController.inflateCircle();
	            
	            stage.show();
	            
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        
		}
		else {
			try {
	        	
	        	FXMLLoader fifthPaneLoader = new FXMLLoader();
	        	fifthPaneLoader.setLocation(getClass().getResource("/venn/VenTestResults.fxml"));
		        Parent fifthPane = fifthPaneLoader.load();
		        Scene fifthScene = new Scene(fifthPane);
		        
		       
		        VennTestResultsController fifthController = (VennTestResultsController) fifthPaneLoader.getController();
		        fifthController.setFourthController(this);
		        fifthController.setFirstController(firstController);
		        fifthScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
	            Stage stage = new Stage();
	            stage.setTitle("VennTest Results");
	            stage.setScene(fifthScene);
	            stage.initStyle(StageStyle.TRANSPARENT);
	            
	            fifthController.showWrongView();
	          
	            stage.show();
	            
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }		}
		
    }
    @FXML
    void uploadButtonAction(ActionEvent event) {
    	
		FileChooser fileChooser = new FileChooser();

		//Extention filter
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extentionFilter);

		//Set to user directory or go to default if cannot access
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		if(!userDirectory.canRead()) {
		    userDirectory = new File("c:/");
		}
		fileChooser.setInitialDirectory(userDirectory);

		//Choose the file
		File chosenFile = fileChooser.showOpenDialog(null);
		//Make sure a file was selected, if not return default
		String path;
		if(chosenFile != null) {
		    path = chosenFile.getPath();
		} else {
		    //default return value
		    path = null;
		}
	
		
		try {
			Scanner sc = new Scanner(chosenFile);  
//			sc.useDelimiter(",");   //sets the delimiter pattern  
			String[] line = sc.nextLine().split(",");
			int j = 70;
			for (int i = 0; i < line.length; i++) {
				canvas.getChildren().addAll(firstController.new EditableLabel(730,j,line[i]));
				j += 30;
			}
			
			leftCorrect = sc.nextLine().split(",");
			intersectionCorrect = sc.nextLine().split(",");
			rightCorrect = sc.nextLine().split(",");
			
			sc.close();  //closes the scanner 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			
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
		Stage stage = (Stage) editor.getScene().getWindow();
	    stage.close();
	}
	
	public void clearAll() {
		
		canvas.getChildren().clear();
		canvas.getChildren().addAll(infoLabel1, infoLabel2, tagsLabel, lCircle, rCircle);
		
	}
	



}
