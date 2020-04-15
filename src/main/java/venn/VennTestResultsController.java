package venn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import venn.DemoController.EditableLabel;

public class VennTestResultsController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane canvas;

    @FXML
    private Circle lCircle;

    @FXML
    private Circle rCircle;

    @FXML
    private Button playAgain;
    
    @FXML
    private Button tryAgain;

    @FXML
    private Button backToEditor;
    
    private VennTestController fourthController;
    private DemoController firstController;
    
    @FXML
    private ImageView wrongIcon;

    @FXML
    private Label wrongInfo1;

    @FXML
    private Label wrongInfo2;

    @FXML
    private Button showAnswer;
    
    @FXML
    private ImageView correctIcon;

    @FXML
    private Label correctInfo1;

    @FXML
    private Label correctInfo2;
    
    public void showCorrectView() {
    	correctInfo1.setVisible(true);
    	correctInfo2.setVisible(true);
    	correctIcon.setVisible(true);
    	playAgain.setVisible(true);
    }
    
    public void showWrongView() {
    	wrongInfo1.setVisible(true);
    	wrongInfo2.setVisible(true);
    	wrongIcon.setVisible(true);
    	tryAgain.setVisible(true);
    	showAnswer.setVisible(true);
    }
    
    public void setFourthController(VennTestController controller) {
        fourthController = controller;
    }
    
    public void setFirstController(DemoController controller) {
        firstController = controller;
    }

    @FXML
    void backToEditorAction(ActionEvent event) {
    	Stage stage = (Stage) backToEditor.getScene().getWindow();
        stage.close();
        fourthController.clearAll();
        fourthController.openFirstScene(event);
        firstController.cT++;
		firstController.formatPaneText.setVisible(false);
		firstController.sidebar.getChildren().remove(firstController.dragInfo);
		firstController.format.setStyle("-fx-background-color: #222831");
    }

    @FXML
    void playAgainAction(ActionEvent event) {
    	Stage stage = (Stage) backToEditor.getScene().getWindow();
        stage.close();
        fourthController.clearAll();
    }
    
    @FXML
    void tryAgainAction(ActionEvent event) {
    	Stage stage = (Stage) backToEditor.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void showAnswerAction(ActionEvent event) {
    	
    	inflateCircle();
	
    }
    
    public void inflateCircle() {
    	int yCoordI = 118;
    	int yCoordL = 106;
    	int yCoordR = 106;
    	
    	for (int i = 0; i < fourthController.intersectionCorrect.length; i++) {

			EditableLabel text = firstController.new EditableLabel(345, yCoordI, fourthController.intersectionCorrect[i]);
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < fourthController.leftCorrect.length; i++) {
			EditableLabel text = firstController.new EditableLabel(154, yCoordL, fourthController.leftCorrect[i]);
			canvas.getChildren().addAll(text);

			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < fourthController.rightCorrect.length; i++) {

			EditableLabel text = firstController.new EditableLabel(500, yCoordR, fourthController.rightCorrect[i]);
			canvas.getChildren().addAll(text);

			yCoordR += 40;
		}
    }
    
    

}
